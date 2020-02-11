package net.tisue.euler

abstract class Problem(number: Int, solution: String) extends munit.FunSuite {
  def solve: Any
  test(s"problem $number") {
    assert(solution == solve.toString)
  }
}
