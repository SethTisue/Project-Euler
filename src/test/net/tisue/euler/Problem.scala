package net.tisue.euler

abstract class Problem(number: Int, solution: String) {
  def main(args: Array[String]): Unit = {
    println(s"problem $number")
    val (result, elapsed) = time(solve)
    println(f"$elapsed%.3f seconds")
    assert(solution == result.toString)
    ()
  }
  def solve: Any
}
