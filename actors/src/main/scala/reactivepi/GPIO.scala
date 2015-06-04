package reactivepi

import akka.actor.{ActorRef, ActorRefFactory}

/**
 * Provides general access to the GPIO ports on your raspberry PI
 */
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
   * Subscribes to a specific input pin. When the value changes, the target actor will
   * receive a GPIO.Data message containing the new value
   *
   * @param pinNumber         Pin number to subscribe to
   * @param actorRefFactory   actorRefFactory to use
   * @return Returns the created actor
   */
  def subscribe(pinNumber: Int, target: ActorRef)(implicit actorRefFactory: ActorRefFactory): ActorRef = {
    actorRefFactory.actorOf(GPIOSubscriptionActor.props(pinNumber, target))
  }

  /**
   * Starts an actor for a specific output pin
   *
   * @param pinNumber        Pin number to use
   * @param actorRefFactory  actorRefFactory to use
   * @return Returns the created actor
   */
  def output(pinNumber: Int)(implicit actorRefFactory: ActorRefFactory): ActorRef = {
    actorRefFactory.actorOf(GPIOOutputActor.props(pinNumber))
  }
}
