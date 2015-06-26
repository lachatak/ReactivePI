package reactivepi

import akka.actor.ActorRefFactory

object I2C {

  case class Read(register: Byte = -1, length: Int)

  case class Data(buffer: Array[Byte])

  case class Write(register: Byte = -1, data: Array[Byte])

}

/**
 * Provides access to the I2C bus through Akka actors
 * @param bus Number of the bus to use (1 or 2 on the raspberry PI)
 * @param actorRefFactory The actor ref factory to use
 */
case class I2C(bus: Int)(implicit actorRefFactory: ActorRefFactory) {
  /**
   * Get access to a device on the specified address
   * @param address Address of the bus
   * @return        Returns the actor for the device
   */
  def device(address: Int) = {
    actorRefFactory.actorOf(I2CDeviceActor.props(bus, address))
  }
}
