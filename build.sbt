scalaVersion := "2.13.11"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M8" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)
