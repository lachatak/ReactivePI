import nl.fizzylogic.reactivepi.i2c.I2CDevice

object Program extends App {
  val MCP9808_REG_CONFIG             = 0x01
  val MCP9808_REG_UPPER_TEMP         = 0x02
  val MCP9808_REG_LOWER_TEMP         = 0x03
  val MCP9808_REG_CRIT_TEMP          = 0x04
  val MCP9808_REG_AMBIENT_TEMP       = 0x05
  val MCP9808_REG_MANUF_ID           = 0x06
  val MCP9808_REG_DEVICE_ID          = 0x07

  val MCP9808_REG_CONFIG_SHUTDOWN    = 0x0100
  val MCP9808_REG_CONFIG_CRITLOCKED  = 0x0080
  val MCP9808_REG_CONFIG_WINLOCKED   = 0x0040
  val MCP9808_REG_CONFIG_INTCLR      = 0x0020
  val MCP9808_REG_CONFIG_ALERTSTAT   = 0x0010
  val MCP9808_REG_CONFIG_ALERTCTRL   = 0x0008
  val MCP9808_REG_CONFIG_ALERTSEL    = 0x0002
  val MCP9808_REG_CONFIG_ALERTPOL    = 0x0002
  val MCP9808_REG_CONFIG_ALERTMODE   = 0x0001

  val device = I2CDevice.open(1,0x18)

  val x1 = wordToInt(device.read(MCP9808_REG_MANUF_ID,2))
  val x2 = wordToInt(device.read(MCP9808_REG_DEVICE_ID, 2))

  println(s"Manufacturer ID: $x1, Device identifier: $x2")

  def wordToInt(data: Array[Byte]):Int = {
    data(1) & 0xFF | data(0) & 0xFF << 8
  }
}
