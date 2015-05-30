package reactivepi.gpio

import java.io._
import java.nio.file.{Files, Paths}

/**
 * Use the input pin class to get access to the value of a GPIO pin
 */
class InputPin(pinNumber: Int) extends GPIOPin(pinNumber, "in") {
  /**
   * Reads the value of the GPIO pin
   * @return  1 when the pin state is high; Otherwise 0.
   */
  def read(): Int = {
    try {
      val readBytes = Files.readAllBytes(Paths.get(pinValueFilePath))
      val pinValue = new String(readBytes)

      Integer.parseInt(pinValue)
    } catch {
      case e:IOException => throw new GPIOException(
        s"Failed to close access to pin ${pinNumber}: ${e.getMessage()}")
    }
  }
}
