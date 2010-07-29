package net.tisue.euler
import Euler._

// Sum the even-valued terms in the Fibonacci sequence which do not exceed one million.

class Problem2 extends Problem(2, "1089154") {
  def solve = {
    lazy val fibs: Stream[Int] = 0 #:: 1 #:: fibs.zipWith(fibs.tail)(_ + _)
    fibs.filter(_ % 2 == 0)
      .takeWhile(_ < 1000000)
      .sum
  }
}
