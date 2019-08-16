scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "com.eed3si9n.verify" %% "verify" % "0.1.0" % Test
)

testFrameworks += new TestFramework("verify.runner.Framework")

scalacOptions ++= Seq(
  "-encoding", "us-ascii",
  "-deprecation",
  "-unchecked",
  "-feature",
  "-Xlint",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)
