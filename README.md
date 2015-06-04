Reactive PI
===========

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

## Development status
I'm working on getting a basic API layed out.
Also, I don't have a good way to test things, this needs
work as well to make my life easier.

Currently the GPIO ports can be controlled. The I2C code is still a work in progress as is the SPI interface.
Keep an eye out for more information as things progress.
