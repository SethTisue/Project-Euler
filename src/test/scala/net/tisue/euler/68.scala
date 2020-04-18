package net.tisue.euler

// What is the maximum 16-digit string for a "magic" 5-gon ring?
// see http://projecteuler.net/index.php?section=problems&id=68 for details

class Problem68 extends Problem(68, "6531031914842725"):
  def isSolution(ns: IndexedSeq[Int]) =
    ns(0) < ns(3) &&
      ns(0) < ns(5) &&
      ns(0) < ns(7) &&
      ns(0) < ns(9) && {
        val sum = ns(0) + ns(1) + ns(2)
        sum == ns(3) + ns(2) + ns(4) &&
          sum == ns(5) + ns(4) + ns(6) &&
          sum == ns(7) + ns(6) + ns(8) &&
          sum == ns(9) + ns(8) + ns(1)
      }
  def expand(ns: IndexedSeq[Int]) =
    Seq(ns(0), ns(1), ns(2), ns(3), ns(2), ns(4),
        ns(5), ns(4), ns(6), ns(7), ns(6), ns(8),
        ns(9), ns(8), ns(1)).mkString
  def solve =
    (1 to 10)
      .permutations
      .filter(isSolution)
      .map(expand)
      .filter(_.size == 16)
      .map(_.toLong)
      .max

