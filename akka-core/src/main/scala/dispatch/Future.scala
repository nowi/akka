/**
 * Copyright (C) 2009-2010 Scalable Solutions AB <http://scalablesolutions.se>
 */

package se.scalablesolutions.akka.dispatch

import java.util.concurrent.locks.ReentrantLock

import java.util.concurrent.TimeUnit

class FutureTimeoutException(message: String) extends RuntimeException(message)

object Futures {

  /**
   * FIXME document
   * <pre>
   * val future = Futures.future(1000) {
   *  ... // do stuff
   * }
   * </pre>
   */
  def future(timeout: Long)(body: => Any): Future = {
    val promise = new DefaultCompletableFuture(timeout)
    try {
      promise completeWithResult body
    } catch {
      case e => promise completeWithException (None, e)
    }
    promise
  }

  def awaitAll(futures: List[Future]): Unit = futures.foreach(_.await)

  def awaitOne(futures: List[Future]): Future = {
    var future: Option[Future] = None
    do {
      future = futures.find(_.isCompleted)
    } while (future.isEmpty)
    future.get
  }

  /*
  def awaitEither(f1: Future, f2: Future): Option[Any] = {
    import Actor.Sender.Self
    import Actor.{spawn, actor}
      
    case class Result(res: Option[Any])
    val handOff = new SynchronousQueue[Option[Any]]
    spawn {
      try {
        println("f1 await")
        f1.await
        println("f1 offer")
        handOff.offer(f1.result)
      } catch {case _ => {}}
    }
    spawn {
      try {
        println("f2 await")
        f2.await
        println("f2 offer")
        println("f2 offer: " + f2.result)
        handOff.offer(f2.result)
      } catch {case _ => {}}
    }
    Thread.sleep(100)
    handOff.take
  }
*/
}

sealed trait Future {
  def await
  def awaitBlocking
  def isCompleted: Boolean
  def isExpired: Boolean
  def timeoutInNanos: Long
  def result: Option[Any]
  def exception: Option[Tuple2[AnyRef, Throwable]]
}

trait CompletableFuture extends Future {
  def completeWithResult(result: Any)
  def completeWithException(toBlame: AnyRef, exception: Throwable)
}

// Based on code from the actorom actor framework by Sergio Bossa [http://code.google.com/p/actorom/].
class DefaultCompletableFuture(timeout: Long) extends CompletableFuture {
  private val TIME_UNIT = TimeUnit.MILLISECONDS
  def this() = this(0)

  val timeoutInNanos = TIME_UNIT.toNanos(timeout)
  private val _startTimeInNanos = currentTimeInNanos
  private val _lock = new ReentrantLock
  private val _signal = _lock.newCondition
  private var _completed: Boolean = _
  private var _result: Option[Any] = None
  private var _exception: Option[Tuple2[AnyRef, Throwable]] = None

  def await = try {
    _lock.lock
    var wait = timeoutInNanos - (currentTimeInNanos - _startTimeInNanos)
    while (!_completed && wait > 0) {
      var start = currentTimeInNanos
      try {
        wait = _signal.awaitNanos(wait)
        if (wait <= 0) throw new FutureTimeoutException("Futures timed out after [" + timeout + "] milliseconds")
      } catch {
        case e: InterruptedException =>
          wait = wait - (currentTimeInNanos - start)
      }
    }
  } finally {
    _lock.unlock
  }

  def awaitBlocking = try {
    _lock.lock
    while (!_completed) {
      _signal.await
    }
  } finally {
    _lock.unlock
  }

  def isCompleted: Boolean = try {
    _lock.lock
    _completed
  } finally {
    _lock.unlock
  }

  def isExpired: Boolean = try {
    _lock.lock
    timeoutInNanos - (currentTimeInNanos - _startTimeInNanos) <= 0
  } finally {
    _lock.unlock
  }

  def result: Option[Any] = try {
    _lock.lock
    _result
  } finally {
    _lock.unlock
  }

  def exception: Option[Tuple2[AnyRef, Throwable]] = try {
    _lock.lock
    _exception
  } finally {
    _lock.unlock
  }

  def completeWithResult(result: Any) = try {
    _lock.lock
    if (!_completed) {
      _completed = true
      _result = Some(result)
    }
  } finally {
    _signal.signalAll
    _lock.unlock
  }

  def completeWithException(toBlame: AnyRef, exception: Throwable) = try {
    _lock.lock
    if (!_completed) {
      _completed = true
      _exception = Some((toBlame, exception))
    }
  } finally {
    _signal.signalAll
    _lock.unlock
  }

  private def currentTimeInNanos: Long = TIME_UNIT.toNanos(System.currentTimeMillis)
}
