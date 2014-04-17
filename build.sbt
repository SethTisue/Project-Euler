scalaVersion := "2.11.0"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" % "scalatest_2.11.0-RC4" % "2.1.3" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
//  "-optimize",   too fragile in 2.11; see https://issues.scala-lang.org/browse/SI-7529
  "-Xlint",
  "-Xfatal-warnings",
  "-Yinline-warnings"
)
