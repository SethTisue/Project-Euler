crossScalaVersions := Seq("2.12.3", "2.11.11")
scalaVersion := crossScalaVersions.value.head

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.4" % "test"

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
