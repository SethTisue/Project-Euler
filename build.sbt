resolvers += Resolver.scalaNightlyRepository
scalaVersion := "3.8.3-RC3"

libraryDependencies += "org.scalameta" %% "munit" % "1.2.4" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature",
  "-Werror", "-source:future", "-Wunused:all",
)
