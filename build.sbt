scalaVersion := "3.3.1"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M10" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)

licenses += License.CC0
