package reactivepi

import akka.actor.{Props, ActorLogging, Actor}
import reactivepi.gpio.{OutputPin, GPIODriver}

object GPIOOutputActor {
  def props(pinNumber: Int) = Props(new GPIOOutputActor(pinNumber))
}

class GPIOOutputActor(pinNumber: Int) extends Actor with ActorLogging {
  var outputPin: OutputPin = null

  def receive = {
    case GPIO.Write(data) => writeDataToPort(data)
  }

  private def writeDataToPort(data: Byte) = {
    if(data != 0) {
      outputPin.high()
    } else {
      outputPin.low()
    }
  }

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    outputPin = GPIODriver.output(pinNumber)
  }

  @throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    super.preRestart(reason, message)
    outputPin.close()
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    outputPin.close()
  }
}
