scalaVersion := "3.0.1-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.26" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  // re-test with every version bump?
  // on BigInt, "Alphanumeric method to is not declared @infix"
  // (https://github.com/lampepfl/dotty/issues/10383)
  // "-source", "3.1",
)
