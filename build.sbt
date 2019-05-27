scalaVersion := crossScalaVersions.value.head
crossScalaVersions := Seq("2.13.0-RC2", "0.15.0-RC1")

Compile / scalaSource := baseDirectory.value / "src" / "main"
Test / scalaSource := baseDirectory.value / "src" / "test"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)

scalacOptions ++= (
  if (isDotty.value)
    Seq("-language:Scala2")
  else
    Seq("-Xlint", "-Ywarn-value-discard")
)


scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xfatal-warnings"
)
