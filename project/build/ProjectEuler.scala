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
    "-unchecked -Xfatal-warnings -encoding us-ascii"
     .split(" ").map(CompileOption).toSeq ++ super.compileOptions

  // distinguish main sources from test sources
  def mainSourceFilter = "Euler.scala"
  def testSourceFilter = "*.scala" - mainSourceFilter
  override def mainSources = descendents(mainSourceRoots, mainSourceFilter)
  override def testSources = descendents(testSourceRoots, testSourceFilter)
  
  /// tests
  // we leave this as false because some problems chew large amounts of RAM,
  // and if we end up running several of those at once we're in trouble - ST 2/24/11
  override def parallelExecution = false
  
  /// ScalaTest
  val scalaToolsSnapshots = ScalaToolsSnapshots // for ScalaTest snapshot
  val scalatest = "org.scalatest" % "scalatest" % "1.4-SNAPSHOT" % "test"

  /// Scalaz
  val scalazCore = "com.googlecode.scalaz" % "scalaz-core_2.8.0" % "5.0"

}
