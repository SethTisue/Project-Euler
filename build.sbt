scalaVersion := "0.27.0-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.14"
testFrameworks += new TestFramework("munit.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings",
  // "-source", "3.1",  // re-test with every version bump
  "-language:implicitConversions",  // TODO for 3.0.0-M1, as per https://github.com/lampepfl/dotty/pull/9935
)
