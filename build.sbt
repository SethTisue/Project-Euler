scalaVersion := "3.1.3-RC4"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M4" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature", "-Xfatal-warnings", "-source:future",
)

licenses += License.CC0
