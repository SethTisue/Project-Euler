scalaVersion := "3.5.0-RC3"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)

licenses += License.CC0
