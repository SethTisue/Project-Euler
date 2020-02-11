package net.tisue.euler

// Sum the even-valued terms in the Fibonacci sequence which do not exceed one million.

class Problem2 extends Problem(2, "1089154") {
  def solve = {
    lazy val fibs: LazyList[Int] =
      0 #:: 1 #::
        (for ((x, y) <- fibs.zip(fibs.tail))
         yield x + y)
    fibs.filter(_ % 2 == 0)
      .takeWhile(_ < 1000000)
      .sum
  }
}
