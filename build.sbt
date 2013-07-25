scalaVersion := "2.11.0-M4"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" % "scalatest_2.10" % "2.0.M5b" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
//  "-optimize",   too fragile in 2.11.0-M4 - ST 7/12/13
  "-Xlint",
  "-Xfatal-warnings",
  "-Yinline-warnings"
)
