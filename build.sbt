resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.10.0-M4"

name := "Seth's Project Euler solutions"

libraryDependencies += "org.scalatest" % "scalatest_2.10.0-M4" % "1.8-SNAPSHOT" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

// we currently omit -Xfatal-warnings because of
// https://issues.scala-lang.org/browse/SI-5927

scalacOptions ++= Seq("-deprecation", "-unchecked", // "-Xfatal-warnings",
                      "-Xlog-reflective-calls",
                      "-feature", "-optimise", "-encoding", "us-ascii")
