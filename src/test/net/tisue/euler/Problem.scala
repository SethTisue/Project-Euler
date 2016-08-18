package net.tisue.euler

import utest._

abstract class Problem(number: Int, solution: String) extends TestSuite {
  val tests = this {
    (s"problem $number") - {
      val (result, elapsed) = time(solve)
      println(f"problem $number%s $elapsed%.3f seconds")
      result.toString ==> solution
      ()
    }
  }
  def solve: Any
}
