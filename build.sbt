scalaVersion := "3.7.2"

libraryDependencies += "org.scalameta" %% "munit" % "1.1.0" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
