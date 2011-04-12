// NOTES:
// - test-only works. no wildcards yet. use tab-completion
// - "show x" or "inspect x" for any setting
// - find out about: retrieveManaged, autoUpdate (enabled by default currently)
scalaVersion := "2.9.0.RC1"

name := "Seth's Project Euler solutions"

resolvers += ScalaToolsSnapshots

libraryDependencies += "org.scalatest" % "scalatest" % "1.4-SNAPSHOT" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")
