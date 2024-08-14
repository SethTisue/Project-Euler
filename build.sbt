scalaVersion := "3.5.0"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.1" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)

licenses += License.CC0
