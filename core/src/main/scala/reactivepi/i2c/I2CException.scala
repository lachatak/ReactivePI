package reactivepi.i2c

/**
 * Gets thrown when something is going wrong with the I2C bus
 * @param message Message for the exception
 */
class I2CException(message: String) extends Exception(message)
