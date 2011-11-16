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
// generate each set from the last, noting new highest n's in a table.  stop when the
// table is full to 200.  prune any search branch with n > 200.

// After having got the right answer with some much-too-slow brute force code, I started googling.
// The sequence is oeis.org/A003313 and according to en.wikipedia.org/wiki/Addition_chain
// "Calculating an addition chain of minimal length is not easy" -- no algorithm exists that is
// simple, general, and fast.

// However from looking at the forums I learned that for small k (and 200 is small), you only need
// to consider "star chains", where you're always including the current largest number in the next
// sum.  With that knowledge the code is simple and fast.

import collection.immutable.{ BitSet => S }

class Problem122 extends Problem(122, "1582") {
  val limit = 200
  var solutions = Map(1 -> 0)
  def next(sets: Set[S]): Set[S] =
    for {
      s <- sets
      n1 = s.max
      n2 <- s.takeWhile(n1 + _ <= limit)
      n = n1 + n2
      if !solutions.contains(n) || solutions(n) == s.size
      set = s + n
    } yield {
      if(!solutions.contains(n)) {
        println((solutions.size, n, set))
        solutions = solutions.updated(n, s.size)
      }
      set
    }
  def solve = {
    var cur = Set(S(1))
    while(solutions.size < limit) {
      cur = next(cur)
      println((solutions.size, cur.size))
    }
    solutions.values.sum
  }
}
