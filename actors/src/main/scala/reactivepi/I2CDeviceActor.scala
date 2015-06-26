package reactivepi

import akka.actor.{Props, Actor}
import reactivepi.i2c.I2CDriver

object I2CDeviceActor {
  def props(bus: Int, address: Int): Props = Props(new I2CDeviceActor(bus,address))
}

class I2CDeviceActor(bus: Int, address: Int) extends Actor {
  private val driver = new I2CDriver(bus,address)

  def receive = {
    case I2C.Read(register, length) => readFromDevice(register, length)
    case I2C.Write(register,data) => writeToDevice(register,data)
  }

  private def writeToDevice(register: Byte, data: Array[Byte]) = {
    if(register == -1) {
      driver.write(data, data.length)
    } else {
      driver.write(register, data, data.length)
    }
  }

  private def readFromDevice(register: Byte, length: Int) = {
    if(register == -1) {
      sender ! I2C.Data(driver.read(length))
    } else {
      sender ! I2C.Data(driver.read(register, length))
    }
  }
}
