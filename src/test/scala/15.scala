package net.tisue.euler

// Starting in the top left corner of a 2x2 grid, there are 6 routes (without backtracking) to the
// bottom right corner. How many routes are there through a 20x20 grid?

// (It's simpler if you use combinatorics.  It's just 40 choose 20, or 40! / (20! * 20!).)

class Problem15 extends Problem(15, "137846528820"):
  def solve =
    val dim = 20
    val mem = Array.ofDim[BigInt](dim + 1, dim + 1)
    def recur(x: Int, y: Int): BigInt =
      val lookup = mem(x)(y)
      if lookup != null then lookup
      else
        val answer =
          if x == 0 || y == 0 then BigInt(1)
          else (0 to y).map(recur(x - 1, _)).sum
        mem(x)(y) = answer
        answer
    recur(dim, dim)
