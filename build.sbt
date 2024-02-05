scalaVersion := "3.4.0-RC4"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M11" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)

licenses += License.CC0
