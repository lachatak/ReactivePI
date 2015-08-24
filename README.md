Reactive PI
===========
[![Build Status](https://travis-ci.org/wmeints/ReactivePI.svg?branch=master)](https://travis-ci.org/wmeints/ReactivePI)

Reactive PI is a library based on akka and scala that allows
developers to use the various I/O ports on their raspberry
PI in a reactive manner.

## Get started
There's two ways in which you can use this library.
First there's the core library, which contains all the driver communication needed
to talk to the various IO ports on your Raspberry PI. Use this if you need basic access to the IO ports.

When you want to work in a more reactive way you can add the actors library to your application.
This library provides actors for talking to the IO ports on your raspberry. Use this if you don't need
low level access to the IO ports

[Please check the wiki for more information](https://github.com/wmeints/ReactivePI/wiki)

## Samples
Want to see how you can work with the GPIO and I2C in practice. I'm still working on a few
samples. One of them is working right now. Checkout the samples/temperature_sensor folder
to see how you can control a adafruit MCP9089 sensor from scala.
