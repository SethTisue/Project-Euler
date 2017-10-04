scalaVersion := "2.12.3"

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

credentials += Credentials(Path.userHome / ".lightbend" / "commercial.credentials")
resolvers += Resolver.url(
  "lightbend-commercial-releases",
  new URL("http://repo.lightbend.com/commercial-releases/"))(
  Resolver.ivyStylePatterns)

addCompilerPlugin(
  "com.lightbend" %% "scala-fortify" % "eda9178e" classifier "assembly")
scalacOptions += s"-P:fortify:build=euler"

scalastyleFailOnWarning := true
scalastyleFailOnError := true
