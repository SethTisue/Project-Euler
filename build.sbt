crossScalaVersions := Seq("2.12.4", "2.11.12", "2.13.0-M3")
scalaVersion := crossScalaVersions.value.head

libraryDependencies += {
  val version =
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 13)) => "3.0.5-M1"
      case _ => "3.0.4"
    }
  "org.scalatest" %% "scalatest" % version % "test"
}

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
