package net.tisue.euler

import org.scalatest.FunSuite

abstract class Problem(number: Int, solution: String) extends FunSuite {
  test(s"problem $number") {
    val (result, elapsed) = time(solve)
    info(f"$elapsed%.3f seconds")
    assert(solution === result.toString)
    ()
  }
  def solve: Any
}
