import sbt._

class ProjectEuler(info: ProjectInfo) extends DefaultProject(info) {

  // override def defaultLoggingLevel = Level.Warn

  log.setTrace(0)  // 0 equals nosbt equals leave out sbt's lines from stack traces

  override def mainScalaSourcePath = "src"
  override def testScalaSourcePath = "src"
  
  /// compiling
  // I removed -optimise until lampsvn.epfl.ch/trac/scala/ticket/3248
  // (marked as dupe of lampsvn.epfl.ch/trac/scala/ticket/3500) is fixed - ST 4/15/10
  override def compileOptions =
    "-g -unchecked -Xfatal-warnings -encoding us-ascii"
     .split(" ").map(CompileOption).toSeq ++ super.compileOptions

  // distinguish main sources from test sources
  def mainSourceFilter = "Euler.scala"
  def testSourceFilter = "*.scala" - mainSourceFilter
  override def mainSources = descendents(mainSourceRoots, mainSourceFilter)
  override def testSources = descendents(testSourceRoots, testSourceFilter)
  
  /// tests
  override def parallelExecution = true
  
  /// ScalaTest
  // val snapshots = "Scala Tools Repository" at
  //   "http://scala-tools.org/repo-snapshots"
  val scalatest = "org.scalatest" % "scalatest" % "1.2" % "test"

  /// Scalaz
  val scalaz = "com.googlecode.scalaz" %% "scalaz-core" % "5.0"

}
