/**
 * Copyright (C) 2009-2010 Scalable Solutions AB <http://scalablesolutions.se>
 */

package se.scalablesolutions.akka.actor

import se.scalablesolutions.akka.util.Logging

import scala.collection.mutable.ListBuffer
import scala.reflect.Manifest

import java.util.concurrent.ConcurrentHashMap

/**
 * Registry holding all Actor instances in the whole system.
 * Mapped by:
 * <ul>
 * <li>the Actor's UUID</li>
 * <li>the Actor's id field (which can be set by user-code)</li>
 * <li>the Actor's class</li>
 * <ul>
 *
 * @author <a href="http://jonasboner.com">Jonas Bon&#233;r</a>
 */
object ActorRegistry extends Logging {
  private val actorsByUUID =      new ConcurrentHashMap[String, Actor]
  private val actorsById =        new ConcurrentHashMap[String, List[Actor]]
  private val actorsByClassName = new ConcurrentHashMap[String, List[Actor]]

  /**
   * Returns all actors in the system.
   */
  def actors: List[Actor] = {
    val all = new ListBuffer[Actor]
    val elements = actorsByUUID.elements
    while (elements.hasMoreElements) all += elements.nextElement
    all.toList
  }

  /**
   * Invokes a function for all actors.
   */
  def foreach(f: (Actor) => Unit) = {
    val elements = actorsByUUID.elements
    while (elements.hasMoreElements) f(elements.nextElement)
  }

  /**
   * Finds all actors that are subtypes of the class passed in as the Manifest argument.
   */
  def actorsFor[T <: Actor](implicit manifest: Manifest[T]): List[T] = {
    val all = new ListBuffer[T]
    val elements = actorsByUUID.elements
    while (elements.hasMoreElements) {
      val actor = elements.nextElement
      if (manifest.erasure.isAssignableFrom(actor.getClass)) {
        all += actor.asInstanceOf[T]
      }
    }
    all.toList
  }

  /**
   * Finds all actors of the exact type specified by the class passed in as the Class argument.
   */
  def actorsFor[T <: Actor](clazz: Class[T]): List[T] = {
    if (actorsByClassName.containsKey(clazz.getName)) {
      actorsByClassName.get(clazz.getName).asInstanceOf[List[T]]
    } else Nil
  }

  /**
   * Finds all actors that has a specific id.
   */
  def actorsFor(id: String): List[Actor] = {
    if (actorsById.containsKey(id)) actorsById.get(id).asInstanceOf[List[Actor]]
    else Nil
  }

   /**
   * Finds the actor that has a specific UUID.
   */
  def actorFor(uuid: String): Option[Actor] = {
    if (actorsByUUID.containsKey(uuid)) Some(actorsByUUID.get(uuid))
    else None
  }

  /**
   * Registers an actor in the ActorRegistry.
   */
  def register(actor: Actor) = {
    // UUID
    actorsByUUID.put(actor.uuid, actor)

    // ID
    val id = actor.getId
    if (id eq null) throw new IllegalStateException("Actor.id is null " + actor)
    if (actorsById.containsKey(id)) actorsById.put(id, actor :: actorsById.get(id))
    else actorsById.put(id, actor :: Nil)

    // Class name
    val className = actor.getClass.getName
    if (actorsByClassName.containsKey(className)) {
      actorsByClassName.put(className, actor :: actorsByClassName.get(className))
    } else actorsByClassName.put(className, actor :: Nil)
  }

  /**
   * Unregisters an actor in the ActorRegistry.
   */
  def unregister(actor: Actor) = {
    actorsByUUID remove actor.uuid
    actorsById remove actor.getId
    actorsByClassName remove actor.getClass.getName
  }

  /**
   * Shuts down and unregisters all actors in the system.
   */
  def shutdownAll = {
    log.info("Shutting down all actors in the system...")
    foreach(_.stop)
    actorsByUUID.clear
    actorsById.clear
    actorsByClassName.clear
    log.info("All actors have been shut down and unregistered from ActorRegistry")
  }
}
