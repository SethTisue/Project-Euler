scalaVersion := "2.11.6"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings",
  "-Yinline-warnings"
)
