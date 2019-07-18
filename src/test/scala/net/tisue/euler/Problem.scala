package net.tisue.euler

import org.junit.Test
import org.junit.Assert._

abstract class Problem(number: Int, solution: String) {
  def solve: Any
  @Test def test(): Unit =
    assertEquals(solution, solve.toString)
}
