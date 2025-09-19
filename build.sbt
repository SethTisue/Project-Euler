scalaVersion := "3.7.3"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.0" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
