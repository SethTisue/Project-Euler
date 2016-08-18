scalaVersion := "2.11.8"

name := "Seth's Project Euler solutions"

libraryDependencies += "com.lihaoyi" %% "utest" % "0.4.3" % "test"
testFrameworks += new TestFramework("utest.runner.Framework")

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

scalastyleFailOnError := true
