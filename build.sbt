crossScalaVersions := Seq("2.12.4", "2.11.12", "2.13.0-M3")
scalaVersion := crossScalaVersions.value.head

// this is the only version available for 2.13.0-M3
libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.5-M1" % "test"

scalaSource in Compile := baseDirectory.value / "src" / "main"

scalaSource in Test := baseDirectory.value / "src" / "test"

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)

scalastyleFailOnWarning := true
scalastyleFailOnError := true
