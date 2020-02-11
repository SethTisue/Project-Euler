scalaVersion := "2.13.1"

libraryDependencies += "org.scalameta" %% "munit" % "0.4.5"
testFrameworks += new TestFramework("munit.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)
