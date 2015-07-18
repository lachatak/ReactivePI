name := "actors"

organization := "nl.fizzylogic.reactivepi"

scalaVersion:= "2.11.6"

libraryDependencies ++= {
  val akkaVersion = "2.3.10"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test"
  )
}
