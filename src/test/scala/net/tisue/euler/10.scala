package net.tisue.euler
import Primes._

// Find the sum of all the primes below two million.

// this is much too slow: primes.takeWhile(_ < 2000000)
// so we use the prime sieve.
class Problem10 extends Problem(10, "142913828922"):
  def solve = primesBelow(2000000).map(BigInt(_)).sum
