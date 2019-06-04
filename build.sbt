scalaVersion := "2.13.0-RC3"

Compile / scalaSource := baseDirectory.value / "src" / "main"
Test / scalaSource := baseDirectory.value / "src" / "test"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)
