package net.tisue.euler
import Memo.memoize

// same code as 114. still runs plenty fast (0.3 seconds) even with increased problem size.

class Problem115 extends Problem(115, "168"):
  def solve(min: Int, lim: Int) =
    lazy val count: Int => Long = memoize:
      start =>
        val results =
          for position <- start to lim - min
              size <- min to lim - position
          yield count(position + size + 1)
        1 + results.sum
    count(0)
  def solve =
    def tooSmall(n: Int) = solve(50, n) <= 1000000
    Iterator.from(1).dropWhile(tooSmall).next
