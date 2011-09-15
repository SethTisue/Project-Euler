package net.tisue.euler
import Primes._

// Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal
// fraction part.

class Problem26 extends Problem(26, "983") {
  def solve = {
    def period(d: Int): Int =
      Stream.from(1).find(p => (BigInt(10) pow p) % d == BigInt(1)).get
    val candidates = primes.drop(3).takeWhile(_ < 1000)
    candidates.maxBy(period)
  }
}
