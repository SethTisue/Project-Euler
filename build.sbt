scalaVersion := "3.0.0-M1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.16"
testFrameworks += new TestFramework("munit.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  // re-test with every version bump. as of 3.0.0-M1 I'm reluctant to
  // enable it because on BigInt,  "Alphanumeric method to is not declared @infix"
  // "-source", "3.1",
)
