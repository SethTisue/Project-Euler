scalaVersion := "2.12.3"

name := "Seth's Project Euler solutions"

libraryDependencies +=
  "org.scalatest" %% "scalatest" % "3.0.3" % "test"

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

autoCompilerPlugins := true
addCompilerPlugin(
  "com.lightbend" %% "scala-fortify" % "08abd94d" classifier "assembly"
    exclude("com.typesafe.conductr", "ent-suite-licenses-parser"))
scalacOptions += s"-P:fortify:out=${target.value}"

scalastyleFailOnError := true
