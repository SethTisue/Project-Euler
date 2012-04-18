scalaVersion := "2.10.0-M2"

name := "Seth's Project Euler solutions"

libraryDependencies += "org.scalatest" % "scalatest_2.9.1" % "1.8.RC1" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings",
                      "-optimise", "-encoding", "us-ascii")
