package net.tisue.euler
import Primes.*

// A composite is a number containing at least two prime factors. For example, 15 = 3 x 5; 9 = 3 x
// 3; 12 = 2 x 2 x 3.  There are ten composites below thirty containing precisely two, not
// necessarily distinct, prime factors: 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.  How many composite
// integers, n < 10^8, have precisely two, not necessarily distinct, prime factors?

// brute force solution. 36 seconds, nearly all of which is the sieving time.

class Problem187 extends Problem(187, "17427258"):
  val limit = 100000000
  val primes = primesBelow(limit / 2)
  def solve =
    primes
      .takeWhile(_ < math.sqrt(limit))
      .map(p =>
        primes.dropWhile(_ < p)
          .takeWhile(p * _ < limit)
          .size)
      .sum
