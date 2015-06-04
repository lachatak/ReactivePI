package reactivepi

import akka.actor.{Actor, ActorLogging, Props}
import reactivepi.gpio.{InputPin, GPIODriver}

object GPIOInputActor {
  def props(pinNumber: Int) = Props(new GPIOInputActor(pinNumber))
}

class GPIOInputActor(pinNumber: Int) extends Actor with ActorLogging {
  var inputPin : InputPin = null

  def receive = {
    case GPIO.Read => readDataFromPort()
  }

  private def readDataFromPort(): Unit = {
    val data = inputPin.read()
    sender ! GPIO.Data(data)
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
