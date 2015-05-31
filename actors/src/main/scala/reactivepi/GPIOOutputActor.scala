package reactivepi

import akka.actor.{Props, ActorLogging, Actor}
import reactivepi.gpio.GPIODriver

object GPIOOutputActor {
  def props(pinNumber: Int) = Props(new GPIOOutputActor(pinNumber))
}

class GPIOOutputActor(pinNumber: Int) extends Actor with ActorLogging {
  val outputPin = GPIODriver.output(pinNumber)

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
  override def postStop(): Unit = {
    super.postStop()
    outputPin.close()
  }
}
