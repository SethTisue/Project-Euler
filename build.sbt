scalaVersion := "3.6.3"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.4" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
