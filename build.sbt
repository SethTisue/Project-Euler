// if ScalaTest isn't available for the Scala version you want to test with,
// you'll need to publish scalatest (and its dependencies: scalacheck+scalactic and scala-xml)
// locally first.  when publishing scalatest, to avoid bnd weirdness you'll need:
//   set publishArtifact in (Compile, packageDoc) in scalatest := false
scalaVersion := "2.13.0-M5"

// this is the only version available for 2.13.0-M5
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.6-SNAP3" % "test"

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
