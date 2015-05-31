package reactivepi.gpio

/**
 * Provides access to the GPIO expansion header on the raspberry PI
 */
object GPIODriver {
  /**
   * Registers a pin as input
   * @param pinNumber Pin number to register
   * @return  Pin input driver
   */
  def input(pinNumber: Int) = {
    new InputPin(pinNumber)
  }

  /**
   * Registers a pin as output
   * @param pinNumber Pin number to register
   * @return  Pin output driver
   */
  def output(pinNumber: Int) = {
    new OutputPin(pinNumber)
  }
}
