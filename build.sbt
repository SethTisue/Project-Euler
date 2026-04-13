resolvers += Resolver.scalaNightlyRepository
scalaVersion := "3.8.4-RC1"

libraryDependencies += "org.scalameta" %% "munit" % "1.3.0" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
