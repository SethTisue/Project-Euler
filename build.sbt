scalaVersion := "3.2.0-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M6" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature", "-Xfatal-warnings", "-source:future",
)

licenses += License.CC0
