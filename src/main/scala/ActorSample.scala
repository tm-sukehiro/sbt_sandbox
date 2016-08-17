import java.net.InetAddress

import scala.actors._

object SillyActor extends Actor {
  def act(): Unit = {
    for (i <- 1 to 5) {
      println("I'm acting!")
      Thread.sleep(1000)
    }
  }
}

object SeriousActor extends Actor {
  def act(): Unit = {
    for (i <- 1 to 5) {
      println("To be or not to be.")
      Thread.sleep(1000)
    }
  }
}

val seriousActor2 = actor {
  for (i <- 1 to 5) {
    println("That is the question.")
    Thread.sleep(1000)
  }
}

// SillyActor.start(); SeriousActor.start()

/// SillyActor ! "hi there"

val echoActor = actor {
  while (true) {
    receive {
      case msg =>
        println("received message: " + msg)
    }
  }
}

object NameResolver extends Actor {
  import java.net.{InetAddress, UnknownHostException}
  def act(): Unit = {
    react {
      case (name: String, actor: Actor) =>
        actor ! getIp(name)
        act()
      case "EXIT" =>
        println("Name resolver exiting.")
      case msg =>
        println("Unhandled message: " + msg)
        act()
    }
  }
  def getIp(name: String): Option[InetAddress] = {
    try {
      Some(InetAddress.getByName(name))
    } catch {
      case _: UnknownHostException => None
    }
  }
}

// NameResolver.start()
// NameResolver ! ("www.scala-lang.org", self)
// self.receiveWithin(0) { case x => x }

object NameResolverLoop extends Actor {
  loop {
    react {
      case (name: String, actor: Actor) =>
        actor ! getIp(name)
      case msg =>
        println("Unhandled message: " + msg)
    }
  }
}

case class LookupIP(name: String, respondTo: Actor)
case class LookupResult(
                       name: String,
                       address: Option[InetAddress]
                       )
object NameResolver2 extends Actor {
  def act(): Unit = {
    loop {
      react {
        case LookupIP(name, actor) =>
          actor ! LookupResult(name, getIp(name))
      }
    }
    def getIp(name: String): Option[InetAddress] = {

    }
  }
}