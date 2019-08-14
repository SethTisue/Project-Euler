package net.tisue.euler
import Memo.memoize

// I solved this from first principles, but after solving it, I looked it up
// and it's www.research.att.com/~njas/sequences/A005252

object Problem114 extends Problem(114, "16475640049") {
  def solve(min: Int, lim: Int) = {
    lazy val count: Int => Long = memoize{start =>
      val results =
        for{position <- start to lim - min
            size <- min to lim - position}
        yield count(position + size + 1)
      1 + results.sum}
    count(0)
  }
  def solve = solve(3, 50)
}
