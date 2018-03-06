package net.tisue.euler
import Primes._

// Find the value of n, 1 < n < 10^7, for which the totient phi(n) is a permutation of n and the ratio
// n/phi(n) produces a minimum.

// We're looking for a number that's a product of exactly two fairly large primes.
// That's the idea behind the optimizations in isSolution.

object Problem70 extends Problem(70, "8319823") {
  // use Stream so we don't compute more factors than we need
  def factors(n: Int): LazyList[Int] =
    if(isSievedPrime(n))
      LazyList(n)
    else {
      val f = primes.find(n % _ == 0).get
      f #:: factors(n / f)
    }
  // pass factors in so we avoid recomputing any we already have
  def totient(n: Int, factors: Seq[Int]) =
    factors.toSet.foldLeft(n.toLong)((t, f) => t * (f - 1) / f).toInt
  def solve(limit: Int) = {
    val factorMin = math.sqrt(limit) / 10 // "fairly large"
    def isSolution(n: Int) = {
      val fs = factors(n)
      fs.head > factorMin && fs.take(3).size == 2 &&
        n.digits.sorted == totient(n, fs).digits.sorted
    }
    // we redundantly recompute the factors here but it doesn't matter
    // since there aren't that many solutions to test
    def ratio(n: Int) = n.toDouble / totient(n, factors(n))
    (2 until limit).filter(isSolution).minBy(ratio)
  }
  def solve = solve(10000000)  // takes about 17 seconds
}
