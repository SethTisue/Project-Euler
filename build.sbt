// PROJECT NOTES:
// - intentionally using ScalaTest 1.3, built for Scala 2.8, to test
//   binary compatibility of 2.9/2.10 with 2.8
// SBT NOTES:
// - test-only works. no wildcards yet. use tab-completion
// - "show x" or "inspect x" for any setting
// - find out about: retrieveManaged, autoUpdate (enabled by default currently)
// - don't put a blank line here
// - can't disable parallel test execution yet
scalaVersion := "2.9.0.RC4"

name := "Seth's Project Euler solutions"

resolvers += ScalaToolsSnapshots

libraryDependencies += "org.scalatest" %% "scalatest" % "1.4.RC4" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings",
	      	      "-encoding", "us-ascii")
