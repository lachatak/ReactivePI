//  Copyright 2015 Willem Meints
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.

import nl.fizzylogic.reactivepi.i2c.{Convert, I2CDevice}

object Program extends App {
  val MCP9808_REG_CONFIG = 0x01
  val MCP9808_REG_UPPER_TEMP = 0x02
  val MCP9808_REG_LOWER_TEMP = 0x03
  val MCP9808_REG_CRIT_TEMP = 0x04
  val MCP9808_REG_AMBIENT_TEMP = 0x05
  val MCP9808_REG_MANUF_ID = 0x06
  val MCP9808_REG_DEVICE_ID = 0x07

  val MCP9808_REG_CONFIG_SHUTDOWN = 0x0100
  val MCP9808_REG_CONFIG_CRITLOCKED = 0x0080
  val MCP9808_REG_CONFIG_WINLOCKED = 0x0040
  val MCP9808_REG_CONFIG_INTCLR = 0x0020
  val MCP9808_REG_CONFIG_ALERTSTAT = 0x0010
  val MCP9808_REG_CONFIG_ALERTCTRL = 0x0008
  val MCP9808_REG_CONFIG_ALERTSEL = 0x0002
  val MCP9808_REG_CONFIG_ALERTPOL = 0x0002
  val MCP9808_REG_CONFIG_ALERTMODE = 0x0001

  val BUS_ID = 1
  val DEVICE_ADDRESS = 0x18


  val device = I2CDevice.open(BUS_ID, DEVICE_ADDRESS)

  val x1 = wordToInt(device.read(MCP9808_REG_MANUF_ID, 2))
  val x2 = wordToInt(device.read(MCP9808_REG_DEVICE_ID, 2))

  val temperature = readTemperature()

  println(s"Manufacturer ID: $x1, Device identifier: $x2")
  println(s"Temperature: $temperature degrees Celcius")

  device.close()

  def readTemperature(): Int = {
    val data = device.read(MCP9808_REG_AMBIENT_TEMP, 2)
    val word = wordToInt(data)

    val temperature: Int = Math.round((word & 0x0FFF) / 16.0).asInstanceOf[Int]

    if ((word & 0x1000) != 0x00) {
      return temperature - 256
    }

    return temperature
  }

  def wordToInt(data: Array[Byte]): Int = {
    return Convert.wordToInt16(data)
  }
}
