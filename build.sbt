ThisBuild / resolvers += Resolver.scalaNightlyRepository
scalaVersion := "3.8.1-RC1-bin-20251117-5ccea40-NIGHTLY"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.1" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
