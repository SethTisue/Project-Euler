scalaVersion := "2.10.0-M6"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" % "scalatest_2.10.0-M6" % "1.9-2.10.0-M6-B2" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-optimise",
  "-Xlint",
  // "-Xfatal-warnings",  pending 2.10 final
  "-Ywarn-adapted-args",
  "-Yinline-warnings"
)
