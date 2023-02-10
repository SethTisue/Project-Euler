scalaVersion := "3.3.0-RC5"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M7" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature", "-Xfatal-warnings", "-source:future",
)

licenses += License.CC0
