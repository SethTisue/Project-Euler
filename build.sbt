scalaVersion := "3.6.4"

libraryDependencies += "org.scalameta" %% "munit" % "1.1.0" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
