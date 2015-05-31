package reactivepi

import akka.actor.{Actor, ActorLogging, Props}
import reactivepi.gpio.GPIODriver

object GPIOInputActor {
  def props(pinNumber: Int) = Props(new GPIOInputActor(pinNumber))
}

class GPIOInputActor(pinNumber: Int) extends Actor with ActorLogging {
  val inputPin = GPIODriver.input(pinNumber)

  def receive = {
    case GPIO.Read => readDataFromPort()
  }

  private def readDataFromPort(): Unit = {
    val data = inputPin.read()
    sender ! GPIO.Data(data)
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    inputPin.close()
  }
}
