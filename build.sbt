scalaVersion := "3.7.4"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.1" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
