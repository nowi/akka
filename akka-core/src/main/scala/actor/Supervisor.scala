/**
 * Copyright (C) 2009-2010 Scalable Solutions AB <http://scalablesolutions.se>
 */

package se.scalablesolutions.akka.actor

import se.scalablesolutions.akka.config.ScalaConfig._
import se.scalablesolutions.akka.config.{AllForOneStrategy, OneForOneStrategy, FaultHandlingStrategy, ConfiguratorRepository, Configurator}
import se.scalablesolutions.akka.util.Helpers._
import se.scalablesolutions.akka.util.Logging
import se.scalablesolutions.akka.dispatch.Dispatchers
import se.scalablesolutions.akka.remote.RemoteServer

import java.util.concurrent.ConcurrentHashMap

/**
 * Abstract base class for all supervisor factories.
 * <p>
 * Example usage:
 * <pre>
 *  val factory = SupervisorFactory(
 *    SupervisorConfig(
 *      RestartStrategy(OneForOne, 3, 10, List(classOf[Exception]),
 *      Supervise(
 *        myFirstActor,
 *        LifeCycle(Permanent)) ::
 *      Supervise(
 *        mySecondActor,
 *        LifeCycle(Permanent)) ::
 *      Nil))
 * </pre>
 *
 * Then create a new Supervisor tree with the concrete Services we have defined.
 *
 * <pre>
 * val supervisor = factory.newInstance
 * supervisor.start // start up all managed servers
 * </pre>
 *
 * @author <a href="http://jonasboner.com">Jonas Bon&#233;r</a>
 */
class SupervisorFactory(val config: SupervisorConfig) extends Logging {
  type ExceptionList = List[Class[_ <: Throwable]]

  def newInstance: Supervisor = newInstanceFor(config)

  def newInstanceFor(config: SupervisorConfig): Supervisor = config match {
    case SupervisorConfig(restartStrategy, _) =>
      val supervisor = create(restartStrategy)
      supervisor.start
      supervisor.configure(config, this)
      supervisor
  }

  protected def create(strategy: RestartStrategy): Supervisor = strategy match {
    case RestartStrategy(scheme, maxNrOfRetries, timeRange, trapExceptions: ExceptionList) =>
      scheme match {
        case AllForOne => new Supervisor(AllForOneStrategy(maxNrOfRetries, timeRange), trapExceptions)
        case OneForOne => new Supervisor(OneForOneStrategy(maxNrOfRetries, timeRange), trapExceptions)
      }
  }
}

object SupervisorFactory {
  def apply(config: SupervisorConfig) = new SupervisorFactory(config)
}

/**
 * <b>NOTE:</b>
 * <p/> 
 * The supervisor class is only used for the configuration system when configuring supervisor
 * hierarchies declaratively. Should not be used as part of the regular programming API. Instead
 * wire the actors together using 'link', 'spawnLink' etc. and set the 'trapExit' flag in the
 * actors that should trap error signals and trigger restart.
 * <p/> 
 * See the ScalaDoc for the SupervisorFactory for an example on how to declaratively wire up actors.
 *
 * @author <a href="http://jonasboner.com">Jonas Bon&#233;r</a>
 */                                  
sealed class Supervisor private[akka] (handler: FaultHandlingStrategy, trapExceptions: List[Class[_ <: Throwable]])
  extends Actor with Logging with Configurator {
  
  trapExit = trapExceptions
  faultHandler = Some(handler)
  dispatcher = Dispatchers.newThreadBasedDispatcher(this)

  private val actors = new ConcurrentHashMap[String, Actor]
  
  // Cheating, should really go through the dispatcher rather than direct access to a CHM
  def getInstance[T](clazz: Class[T]) = actors.get(clazz.getName).asInstanceOf[T]
  def getComponentInterfaces: List[Class[_]] = actors.values.toArray.toList.map(_.getClass)
  def isDefined(clazz: Class[_]): Boolean = actors.containsKey(clazz.getName)

  override def start: Actor = synchronized {
    ConfiguratorRepository.registerConfigurator(this)
    super[Actor].start
  }
  
  override def stop = synchronized {
    super[Actor].stop
    getLinkedActors.toArray.toList.asInstanceOf[List[Actor]].foreach { actor =>
      actor.stop
      log.info("Shutting actor down: %s", actor)
    }
    log.info("Stopping supervisor: %s", this)
  }

  def receive = {
    case unknown => throw new IllegalArgumentException("Supervisor " + toString + " does not respond to any messages. Unknown message [" + unknown + "]")
  }

  def configure(config: SupervisorConfig, factory: SupervisorFactory) = config match {
    case SupervisorConfig(_, servers) =>
      servers.map(server =>
        server match {
          case Supervise(actor, lifeCycle, remoteAddress) =>
            actors.put(actor.getClass.getName, actor)
            actor.lifeCycle = Some(lifeCycle)
            startLink(actor)
            remoteAddress.foreach(address => RemoteServer.actorsFor(RemoteServer.Address(address.hostname, address.port)).actors.put(actor.getId, actor))

           case supervisorConfig @ SupervisorConfig(_, _) => // recursive supervisor configuration
             val supervisor = factory.newInstanceFor(supervisorConfig).start
             supervisor.lifeCycle = Some(LifeCycle(Permanent))
             actors.put(supervisor.getClass.getName, supervisor)
             link(supervisor)
        })
  }
}
