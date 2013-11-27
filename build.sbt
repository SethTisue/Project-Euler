scalaVersion := "2.11.0-M7"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "2.0.1-SNAP4" % "test"

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
