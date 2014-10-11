scalaVersion := "2.11.3"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "2.2.1" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Xfatal-warnings",
  "-Yinline-warnings"
)
