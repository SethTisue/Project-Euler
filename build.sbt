scalaVersion := "2.10.0"

name := "Seth's Project Euler solutions"

// the "_2.10.0-RC5" thing can be dropped once Bill releases for 2.10.0 - ST 11/17/12
libraryDependencies +=
  "org.scalatest" % "scalatest_2.10.0-RC5" % "2.0.M5-B1" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Xfatal-warnings"
)
