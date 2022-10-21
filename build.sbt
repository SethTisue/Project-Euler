scalaVersion := "3.2.1-RC4"

libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M6" % Test

scalacOptions ++= Seq(
  "-encoding", "us-ascii", "-deprecation", "-feature", "-Xfatal-warnings", "-source:future",
  "-Yexplicit-nulls", "-language:unsafeNulls",
)

licenses += License.CC0
