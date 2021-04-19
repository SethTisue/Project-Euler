scalaVersion := "3.0.0-RC3"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.25" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  // re-test with every version bump. as of 3.0.0-M3 I'm reluctant to
  // enable it because on BigInt, "Alphanumeric method to is not declared @infix"
  // (https://github.com/lampepfl/dotty/issues/10383)
  // "-source", "3.1",
)
