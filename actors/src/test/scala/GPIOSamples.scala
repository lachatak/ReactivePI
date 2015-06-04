import akka.actor.{Props, ActorSystem, Actor}
import reactivepi.GPIO

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