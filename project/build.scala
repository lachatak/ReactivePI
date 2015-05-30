import sbt._
import Keys._

object ReactivePIBuild extends Build {
  lazy val core = Project(id = "core", base = file("core"))
  lazy val reactivepi = Project(id = "reactivepi", base = file(".")) aggregate("core")
}
