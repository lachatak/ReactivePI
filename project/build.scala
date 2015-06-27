import sbt._
import Keys._

object ReactivePIBuild extends Build {
  lazy val core = Project(id = "core", base = file("core"))
  lazy val actors = Project(id = "actors",
    base = file("actors")) dependsOn("core")

  lazy val reactivepi = Project(id = "nl.fizzylogic.reactivepi",
    base = file(".")) aggregate("core","actors")
}
