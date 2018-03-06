package net.tisue.euler
import Primes._

// What is the smallest odd composite that cannot be written as the sum of a prime and twice a
// square?

object Problem46 extends Problem(46, "5777") {
  def square(n: Int) = n * n
  def isSquare(n: Int) =
    n == square(math.round(math.sqrt(n)).toInt)
  def hasSolution(n: Int) =
    primes.takeWhile(_ < n)
      .exists{p => val diff = n - p; diff % 2 == 0 && isSquare(diff / 2)}
  def solve =
    LazyList.from(3, 2)
      .find(n => !isSievedPrime(n) && !hasSolution(n))
      .get
}
