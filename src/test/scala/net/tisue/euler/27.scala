package net.tisue.euler
import Primes._

// Considering quadratics of the form:
// n^2 + an + b, where |a| < 1000 and |b| < 1000
// where |n| is the absolute value of n
// Find the product of the coefficients, a and b, for the quadratic
// expression that produces the maximum number of primes for consecutive
// values of n, starting with n = 0.

// takes 11 seconds

class Problem27 extends Problem(27, "-59231"):
  def solve =
    def primeCount(a: Int, b: Int): Int =
      def polynomial(n: Int) = n * (a + n) + b
      LazyList.from(0)
        .map(polynomial)
        .takeWhile(p => p > 0 && isSievedPrime(p))
        .size
    def search(limit: Int) =
      val range = -limit to limit
      val candidates = for a <- range
                           b <- range
                       yield (a, b)
      val (a, b) = candidates.maxBy((primeCount _).tupled)
      a * b
    search(999)
