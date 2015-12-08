scalaVersion := "2.11.7"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "junit" % "junit" % "4.12" % "test"

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
