package net.tisue.euler
import Primes._

class Problem123 extends Problem(123, "21035") {
  def r(n: Int) = {
    val p = BigInt(primes(n - 1))
    ((p - 1).pow(n) + (p + 1).pow(n)) % (p * p)
  }
  def solve = {
    def tooBig(n: Int) = r(n * 2 - 1) > 10000000000L  // 10 ^ 9
    val lowerBound =
      LazyList.iterate(1)(_ * 2).takeWhile(!tooBig(_)).last
    binarySearch(lowerBound, lowerBound * 2)(tooBig) * 2 + 1
  }
}
