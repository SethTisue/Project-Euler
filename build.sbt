scalaOrganization := "ch.epfl.lamp"
scalaVersion := "0.1-SNAPSHOT"
autoScalaLibrary := false
libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.5"
scalaBinaryVersion := "2.11"
scalaCompilerBridgeSource :=
  ("ch.epfl.lamp" % "dotty-bridge" % "0.1.1-SNAPSHOT" % "component").sources()

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.0" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature"
)

scalastyleFailOnError := true
