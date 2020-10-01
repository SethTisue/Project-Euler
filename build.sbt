scalaVersion := "0.27.0-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.13"
testFrameworks += new TestFramework("munit.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  "-language:implicitConversions",  // TODO
)
