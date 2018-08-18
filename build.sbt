// for this to work except in the community build, you'll need to
// publish scalatest (and its dependencies: scalacheck+scalactic and scala-xml)
// locally first.  for scalatest, to avoid bnd weirdness you'll need:
//   set publishArtifact in (Compile, packageDoc) in scalatest := false
scalaVersion := "2.13.0-pre-caeddb7"  // August 18

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
