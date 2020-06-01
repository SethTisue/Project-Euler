scalaVersion := "0.24.0-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.8"
testFrameworks += new TestFramework("munit.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-language:implicitConversions",  // TODO
)
