resolvers += "scala-pr" at
  "https://scala-ci.typesafe.com/artifactory/scala-pr-validation-snapshots/"
scalaVersion := "2.13.0-pre-08218cd-SNAPSHOT"

Compile / scalaSource := baseDirectory.value / "src" / "main"
Test / scalaSource := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)
