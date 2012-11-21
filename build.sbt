scalaVersion := "2.10.0-RC2"

name := "Seth's Project Euler solutions"

// the "cross" thing can be dropped once 2.10.0 final is out - ST 11/17/12
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "2.0.M5" % "test" cross(CrossVersion.full)

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
