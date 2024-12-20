scalaVersion := "3.6.2"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.3" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
