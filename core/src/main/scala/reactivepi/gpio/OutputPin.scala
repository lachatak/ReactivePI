package reactivepi.gpio

import java.io._

/**
 * Use the output pin class to configure a pin on the
 * GPIO connector of your raspberry PI as output
 * @param pinNumber Pin number to use
 */
class OutputPin(pinNumber: Int) extends GPIOPin(pinNumber, "out") {
  /**
   * Sets the output pin to a low state
   */
  def low(): Unit = {
    try {
      val pinValueWriter = new PrintWriter(new File(pinValueFilePath))

      pinValueWriter.write("0")
      pinValueWriter.close()
    } catch {
      case e:IOException => throw new GPIOException(
        s"Failed to set the pin state for pin ${pinNumber}: ${e.getMessage()}")
    }
  }

  /**
   * Sets the output pin to a high state
   */
  def high(): Unit = {
    try {
      val pinValueWriter = new PrintWriter(new File(pinValueFilePath))

      pinValueWriter.write("1")
      pinValueWriter.close()
    } catch {
      case e:IOException => throw new GPIOException(
        s"Failed to set the pin state for pin ${pinNumber}: ${e.getMessage()}")
    }
  }
}
