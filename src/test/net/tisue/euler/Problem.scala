package net.tisue.euler

abstract class Problem(number: Int, solution: String) {
  def test() = {
    println(s"problem $number")
    val (result, elapsed) = time(solve)
    // println(s"$elapsed%.3f seconds")
    assert(solution == result.toString)
    ()
  }
  def solve: Any
}
