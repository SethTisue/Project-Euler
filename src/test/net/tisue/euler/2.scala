package net.tisue.euler

// Sum the even-valued terms in the Fibonacci sequence which do not exceed one million.

object Problem2 extends Problem(2, "1089154") {
  def solve = {
    lazy val fibs: LazyList[Int] = 0 #:: 1 #:: fibs.lazyZip(fibs.tail).map(_ + _)
    fibs.filter(_ % 2 == 0)
      .takeWhile(_ < 1000000)
      .sum
  }
}
