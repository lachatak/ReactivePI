name := "ReactivePI"

version := "0.0.1"

scalaVersion := "2.11.6"

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

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
