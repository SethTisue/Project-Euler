scalaVersion := "3.3.0"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M7" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)

licenses += License.CC0
