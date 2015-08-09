#include <jni.h>
#import "nl_fizzylogic_reactivepi_i2c_I2CDevice.h"

JNIEXPORT jint JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_initializeDevice
    (JNIEnv * env, jobject instance, jstring path)
{
    return -1;
}

JNIEXPORT void JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_closeDevice
    (JNIEnv * env, jobject instance, jint handle)
{

}

JNIEXPORT jint JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_deviceWriteDirect
    (JNIEnv * env, jobject instance, jint handle, jint deviceAddress, jbyteArray data, jint offset, jint length)
{
    return -1;
}

JNIEXPORT jint JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_deviceWrite
    (JNIEnv *env, jobject instance, jint handle, jint deviceAddress, jint deviceRegister, jbyteArray data, jint offset, jint length)
{
    return -1;
}

JNIEXPORT jint JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_deviceReadDirect
    (JNIEnv *env, jobject instance, jint handle, jint deviceAddress, jbyteArray data, jint offset, jint length)
{
    return -1;
}

JNIEXPORT jint JNICALL Java_nl_fizzylogic_reactivepi_i2c_I2CDevice_deviceRead
    (JNIEnv *env, jobject instance, jint handle, jint deviceAddress, jint deviceRegister, jbyteArray data, jint offset, jint length)
{
    return -1;
}