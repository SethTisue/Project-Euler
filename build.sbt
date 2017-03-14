scalaVersion := "2.12.1"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"

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

scalacOptions ++=
  Seq(
    "-Xplugin:/Users/tisue/scala-fortify/target/scala-2.12/scala-fortify_2.12-359c2338.jar",
    "-Xplugin-require:fortify",
    s"-P:fortify:out=${target.value}")

scalastyleFailOnError := true
