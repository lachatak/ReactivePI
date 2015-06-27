package nl.fizzylogic.reactivepi.gpio

import java.io.{File, IOException, PrintWriter}

abstract class GPIOPin(pinNumber: Int, direction: String) {
  val pinDirectionFilePath = s"/sys/class/gpio${pinNumber}/direction"
  val pinRegistrationFilePath = s"/sys/class/gpio/export"
  val pinUnregistrationFilePath = s"/sys/class/gpio/unexport"
  val pinValueFilePath = s"/sys/class/gpio/gpio${pinNumber}/value"

  try {
    val pinRegistrationWriter = new PrintWriter(new File(pinRegistrationFilePath))
    val pinModeWriter = new PrintWriter(new File(pinDirectionFilePath))

    // Write the pin number to /sys/class/gpio/export to make the pin active.
    pinRegistrationWriter.write(pinNumber.toString())

    // Write the word 'out/in' to /sys/class/gpio/gpio{pinnumber}/direction to make it an output or input pin.
    pinModeWriter.write(direction)
    pinModeWriter.close()
  } catch {
    case e: IOException => throw new GPIOException(
      s"Failed to configure pin ${pinNumber}: ${e.getMessage()}")
  }

  /**
   * Closes access to the output pin
   */
  def close(): Unit = {
    try {
      val pinRegistrationWriter = new PrintWriter(new File(pinUnregistrationFilePath))

      // Write the number of the pin to /sys/class/gpio/unexport to close access to it
      pinRegistrationWriter.write(pinNumber.toString())
      pinRegistrationWriter.close()
    } catch {
      case e:IOException => throw new GPIOException(
        s"Failed to close access to pin ${pinNumber}: ${e.getMessage()}")
    }
  }
}
