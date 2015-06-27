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

import akka.actor.{Props, ActorSystem, Actor}
import nl.fizzylogic.reactivepi.GPIO

class GPIOSamples extends Actor {
  val input = GPIO.input(1)
  val output = GPIO.output(2)

  //input ! GPIO.Read
  input.tell(GPIO.Read, self)

  def receive = {
    case GPIO.Data(val) => output ! GPIO.Write(val)
  }
}

class GPIOSubscription extends Actor {
  val input = GPIO.subscribe(3, self)
  val output = GPIO.output(4)

  def receive = {
    case GPIO.Data(val) => output ! GPIO.Write(val)
  }
}

object GPIOSamplesApplication extends App {
  implicit val actorSystem = ActorSystem("gpio-controller")
  val actor = actorSystem.actorOf(Props(new GPIOSamples()))

  actorSystem.stop()
}