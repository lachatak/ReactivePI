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

package reactivepi.i2c

import java.io.RandomAccessFile

/**
 * Provides access to the I2C bus on the raspberry PI
 * @param bus The bus number (1 or 2) on a raspberry PI.
 * @param address The address of the slave device on the selected bus
 */
class I2CDriver(bus: Int, address: Int) {
  val deviceFile = new RandomAccessFile(s"/dev/i2c-${bus}", "rw")

  /**
   * Reads a range of bytes from the device
   * @param length  Number of bytes to read
   * @return        Returns the data read from the device.
   */
  def read(length: Int) = {
    val data = new Array[Byte](length)
    val readBytes = deviceFile.read(data, 0, length)

    if (readBytes != length) {
      throw new I2CException("Failed to read the full buffer length from the device. " +
        "Please make sure that your device accepts the requested buffer length for reading.")
    }

    data
  }

  /**
   * Reads a range of data from a specific device register
   * @param registerAddress Address of the register to read
   * @param length  Number of bytes to read
   * @return        Returns the data read from the device
   */
  def read(registerAddress: Byte, length: Int) = {
    val data = new Array[Byte](length)
    val addressBuffer = Array(registerAddress)

    deviceFile.write(addressBuffer, 0, 1)
    val readBytes = deviceFile.read(data, 0, length)

    if (readBytes != length) {
      throw new I2CException("Failed to read the full buffer length from the device. " +
        "Please make sure that your device accepts the requested buffer length for reading.")
    }

    data
  }

  /**
   * Writes a range of data to the device
   * @param data    Data to write
   * @param length  Number of bytes to write
   */
  def write(data: Array[Byte], length: Int): Unit = {
    val bytesWritten = deviceFile.write(data,0,length)
  }

  /**
   * Writes a range of data to the device
   * @param data    Data to write
   * @param length  Number of bytes to write
   */
  def write(registerAddress:Byte, data: Array[Byte], length: Int): Unit = {
    val bytesWritten = deviceFile.write(Array(registerAddress) ++ data,0,1)
  }

  /**
   * Writes a single byte to the device
   * @param data  Data to send
   */
  def write(data: Byte) = {
    val bytesWritten = deviceFile.write(Array(data),0,1)
  }

  /**
   * Closes access to the I2C device
   */
  def close() = {
    deviceFile.close()
  }
}
