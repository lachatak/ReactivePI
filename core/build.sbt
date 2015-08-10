name := "core"

organization := "nl.fizzylogic.reactivepi"

scalaVersion := "2.11.6"



val nativeClasses = List(
  "nl.fizzylogic.reactivepi.i2c.I2CDevice"
)

val nativeDeviceSources = List(
  "src/jni/nl_fizzylogic_reactivepi_i2c_I2CDevice.c"
)

val nativeGenerateHeaders = taskKey[Int]("Generates JNI headers for the library")

nativeGenerateHeaders := {
  ("javah -classpath target/scala-2.11/classes -d src/jni " + nativeClasses.mkString(" ")) !
}
