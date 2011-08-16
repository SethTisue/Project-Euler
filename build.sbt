scalaVersion := "2.9.1.RC3"

name := "Seth's Project Euler solutions"

libraryDependencies += "org.scalatest" % "scalatest_2.9.0" % "1.6.1" % "test"

scalaSource in Compile <<= baseDirectory(_ / "src" / "main")

scalaSource in Test <<= baseDirectory(_ / "src" / "test")

scalacOptions ++= Seq("-deprecation", "-unchecked", "-Xfatal-warnings",
	      	      "-encoding", "us-ascii")
