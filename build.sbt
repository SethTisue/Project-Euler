ThisBuild / resolvers += Resolver.scalaNightlyRepository
scalaVersion := "3.8.0-RC2"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.1" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
