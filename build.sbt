scalaVersion := "3.5.2"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.2" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
  "-Yexplicit-nulls"
)
