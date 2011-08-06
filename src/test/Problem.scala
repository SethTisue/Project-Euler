package net.tisue.euler

import org.scalatest.FunSuite

abstract class Problem(number: Int, solution: String) extends FunSuite {
  test("problem " + number) {
    val (result, elapsed) = Euler.time(solve)
    info("%.3f seconds".format(elapsed))
    assert(solution === result.toString)
  }
  def solve: Any
}
