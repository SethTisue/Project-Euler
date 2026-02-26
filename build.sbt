ThisBuild / resolvers += Resolver.scalaNightlyRepository
scalaVersion := "3.8.3-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.2" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
