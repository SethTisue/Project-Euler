package net.tisue.euler

import org.scalatest.FunSuite

abstract class Problem(number: Int, expected: String) extends FunSuite {
  test("problem " + number) {
    val (result, elapsed) = Euler.time(solve)
    info(format("%.3f seconds", elapsed))
    assert(expected === result.toString)
  }
  def solve: Any
}
