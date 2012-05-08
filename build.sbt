resolvers += ScalaToolsSnapshots

scalaVersion := "2.10.0-M3"

name := "Seth's Project Euler solutions"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8-SNAPSHOT" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

// we currently omit -Xfatal-warnings because of
// https://issues.scala-lang.org/browse/SI-5542

scalacOptions ++= Seq("-deprecation", "-unchecked",
                      "-feature", "-optimise", "-encoding", "us-ascii")
