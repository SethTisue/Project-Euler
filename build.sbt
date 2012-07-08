scalaVersion := "2.10.0-M4"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" % "scalatest_2.10.0-M4" % "1.9-2.10.0-M4-B2" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

// we currently omit -Xfatal-warnings because of
// https://issues.scala-lang.org/browse/SI-5927

// supposedly in M5 -target:jvm-1.5-asm will be default - ST 7/7/12

scalacOptions ++= Seq("-deprecation", "-unchecked", // "-Xfatal-warnings",
                      "-target:jvm-1.5-asm",
                      "-feature", "-optimise", "-encoding", "us-ascii")
