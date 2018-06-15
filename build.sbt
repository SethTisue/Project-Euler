scalaVersion := "2.13.0-M4"

// this is the only version available for 2.13.0-M4
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.6-SNAP1" % "test"

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
