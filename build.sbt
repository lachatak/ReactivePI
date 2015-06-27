name := "ReactivePI"

version := "0.1-SNAPSHOT"

organization := "nl.fizzylogic.reactivepi"

scalaVersion := "2.11.6"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>http://github.com/wmeints/reactivePI</url>
  <licenses>
    <license>
      <name>Apache2</name>
      <url>https://raw.githubusercontent.com/wmeints/ReactivePI/master/LICENSE</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:wmeints/reactivepi.git</url>
    <connection>scm:git:git@github.com:wmeints/reactivepi.git</connection>
  </scm>
  <developers>
    <developer>
      <id>wmeints</id>
      <name>Willem Meints</name>
      <url>http://www.fizzylogic.nl/</url>
    </developer>
  </developers>)

