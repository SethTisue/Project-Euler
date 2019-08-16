package net.tisue.euler

// All square roots are periodic when written as continued fractions.  Exactly four continued
// fractions, for N <= 13, have an odd period.  How many continued fractions for N <= 10000 have an
// odd period?

// After looking at the forum solutions I realize the cycle-detection code in the following is much
// more general than is needed.  The cycle always begins right at the start, so we don't need to
// consider the possibility of non-repeating junk at the beginning, so there's no need for a map --
// we only need to remember what the beginning of the iteration looked like so when we return
// to the beginning we recognize it.

object Problem64 extends Problem(64, "1322") {
  def period(n: Int) = {
    val m = math.sqrt(n).toInt
    val seen = new collection.mutable.HashMap[(Int, Int), Int]
    def iterate(num: Int, c: Int, count: Int): Int =
      seen.get((num, c)) match {
        case Some(oldCount) =>
          count - oldCount
        case None =>
          seen((num, c)) = count
          val denom = n - c * c
          val nextA = num * (m + c) / denom
          iterate(denom / num,
                  (nextA * denom - num * c) / num,
                  count + 1)
      }
    if(m * m == n)
      0
    else
      iterate(1, m, 0)
  }
  def solve = (2 to 10000).count(period(_) %2 == 1)
}
