package net.tisue.euler

// m(k) is the minimum number of multiplications to compute n^k.  e.g. m(15) = 5.
// (Because 1 + 1 = 2, 2 + 1 = 3, 3 + 3 = 6, 6 + 6 = 12, 12 + 3 = 15.)
// For 1 <= k <= 200, find the sum of m(k).

// solution plan:
//   0 steps: ((1))
//   1 step : ((1 2))
//   2 steps: ((1 2 3) (1 2 4))
//   3 steps: ((1 2 3 4) (1 2 3 5) (1 2 4 6) (1 2 4 7) (1 2 4 8))
//   ...
// generate each set from the last, noting new highest n's in a table.  stop when the table is full
// to 200.  prune any search branch with n > 200.

import collection.immutable.{ BitSet => S }

class Problem122 extends Problem(122, "1582") {
  val limit = 200
  var solutions = Map(1 -> 1)
  def next(sets: List[S]): List[S] =
    for {
      s <- sets
      n1 <- s
      n2 <- s.toList.dropWhile(n1 + _ <= s.max).takeWhile(n1 + _ <= limit)
    } yield {
      val set = s + (n1 + n2)
      if(!solutions.contains(set.max)) {
        println((solutions.size, set.max, set))
        solutions = solutions.updated(set.max, set.size - 1)
      }
      set
    }
  def solve = {
    var cur = List(S(1))
    while(solutions.size < limit) {
      println((solutions.size, cur.size))
      cur = next(cur)
    }
    solutions.values.sum
  }
}
