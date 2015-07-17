import sbt._
import Keys._

import sbt._
import Keys._
import org.scalastyle.sbt.ScalastylePlugin

object BuildSettings {

  lazy val buildSettings =
    Defaults.defaultSettings ++
      Seq(
        version := "1.0",
        scalaVersion in ThisBuild := "2.11.7",
        parallelExecution in ThisBuild := false,
        scalacOptions := Seq(
          "-feature",
          "-language:implicitConversions",
          "-language:postfixOps",
          "-deprecation",
          "-encoding", "utf8",
          "-Ywarn-adapted-args"))

}

object ReactivePIBuild extends Build {
  import BuildSettings._

  // Common settings for all build modules.
  val commonSettings = buildSettings ++ Seq(ScalastylePlugin.buildSettings: _*)

  lazy val core = Project(id = "core", base = file("core"), settings = commonSettings)
  lazy val actors = Project(id = "actors", base = file("actors"), settings = commonSettings) dependsOn("core")

  lazy val reactivepi = Project(id = "reactivepi",
    base = file(".")) aggregate("core","actors")
}
