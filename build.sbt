scalaVersion := "3.1.1-RC2"

libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings",
  "-source:future",
)
