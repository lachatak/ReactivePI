package reactivepi

import akka.actor.{Props, ActorRef, Actor}
import reactivepi.gpio.{InputPin, GPIODriver}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

object GPIOSubscriptionActor {
  val Tick = "tick"

  def props(pinNumber: Int, target: ActorRef) = Props(new GPIOSubscriptionActor(pinNumber, target))
}

class GPIOSubscriptionActor(pinNumber: Int, target: ActorRef) extends Actor {

  import GPIOSubscriptionActor._

  var inputPin:InputPin = null
  var currentValue = inputPin.read()

  implicit val ec: ExecutionContext = context.dispatcher

  // Read the initial value and start the scheduler
  readInputValue(initial = true)
  context.system.scheduler.schedule(1 millisecond, 1 millisecond, self, Tick)

  // Every time the timeout on the scheduler is triggered
  // the input value is read and send to the target actor.
  def receive = {
    case Tick => readInputValue(initial = false)
  }

  private def readInputValue(initial: Boolean): Unit = {
    val inputValue = inputPin.read()

    if(inputValue != currentValue || initial) {
      target ! GPIO.Data(inputValue)
    }

    currentValue = inputValue
  }

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    inputPin = GPIODriver.input(pinNumber)
  }

  @throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    super.preRestart(reason, message)
    inputPin.close()
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    inputPin.close()
  }
}
