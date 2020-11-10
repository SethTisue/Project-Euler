package net.tisue.euler
import Memo.memoize

// hardly any different from 114 & 115

class Problem116 extends Problem(116, "20492570929"):
  def solve(size: Int, tileSize: Int) =
    lazy val count: Int => Long = memoize{start =>
      val results =
        for position <- start to size - tileSize
        yield 1 + count(position + tileSize)
      results.sum
    }
    count(0)
  def solve = (2 to 4).map(solve(50, _)).sum

