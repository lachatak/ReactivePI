package reactivepi

import akka.actor.{ActorRef, ActorRefFactory}

object GPIO {

  case object Read

  case class Write(data: Byte)

  case class Data(value: Byte)

  /**
   * Starts an actor for a specific input pin
   *
   * @param pinNumber       Pin number to use
   * @param actorRefFactory actorRefFactory to use
   * @return Returns the created actor
   */
  def input(pinNumber: Int)(implicit actorRefFactory: ActorRefFactory): ActorRef = {
    actorRefFactory.actorOf(GPIOInputActor.props(pinNumber))
  }

  /**
   * Starts an actor for a specific output pin
   * @param pinNumber        Pin number to use
   * @param actorRefFactory  actorRefFactory to use
   * @return Returns the created actor
   */
  def output(pinNumber: Int)(implicit actorRefFactory: ActorRefFactory): ActorRef = {
    actorRefFactory.actorOf(GPIOOutputActor.props(pinNumber))
  }
}
