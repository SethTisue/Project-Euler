package net.tisue.euler

import verify._

abstract class Problem(number: Int, solution: String) extends BasicTestSuite {
  def solve: Any
  test(s"problem $number") {
    assert(solution == solve.toString)
  }
}
