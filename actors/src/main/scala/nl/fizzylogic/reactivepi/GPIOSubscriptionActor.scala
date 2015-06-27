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


package nl.fizzylogic.reactivepi

import akka.actor.{Props, ActorRef, Actor}
import nl.fizzylogic.reactivepi.gpio.{InputPin, GPIODriver}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

object GPIOSubscriptionActor {
  val Tick = "tick"

  def props(pinNumber: Int, target: ActorRef) = Props(new GPIOSubscriptionActor(pinNumber, target))
}

class GPIOSubscriptionActor(pinNumber: Int, target: ActorRef) extends Actor {

  import GPIOSubscriptionActor._

  var inputPin:InputPin = null
  var currentValue = inputPin.read()

  implicit val ec: ExecutionContext = context.dispatcher

  // Read the initial value and start the scheduler
  readInputValue(initial = true)
  context.system.scheduler.schedule(1 millisecond, 1 millisecond, self, Tick)

  // Every time the timeout on the scheduler is triggered
  // the input value is read and send to the target actor.
  def receive = {
    case Tick => readInputValue(initial = false)
  }

  private def readInputValue(initial: Boolean): Unit = {
    val inputValue = inputPin.read()

    if(inputValue != currentValue || initial) {
      target ! GPIO.Data(inputValue)
    }

    currentValue = inputValue
  }

  @throws[Exception](classOf[Exception])
  override def preStart(): Unit = {
    inputPin = GPIODriver.input(pinNumber)
  }

  @throws[Exception](classOf[Exception])
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    super.preRestart(reason, message)
    inputPin.close()
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    inputPin.close()
  }
}
