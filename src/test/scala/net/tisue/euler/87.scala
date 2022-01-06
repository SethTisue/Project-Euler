package net.tisue.euler

import Primes.*

// How many numbers below fifty million can be expressed as the sum of a prime square, prime cube, and
// prime fourth power?  (There are exactly four such numbers below fifty.)

class Problem87 extends Problem(87, "1097343"):
  def solve(limit: Int) =
    val powers2 = primes.map(n => n * n        ).takeWhile(_ < limit)
    val powers3 = primes.map(n => n * n * n    ).takeWhile(_ < limit)
    val powers4 = primes.map(n => n * n * n * n).takeWhile(_ < limit)
    val solutions =
      for p4 <- powers4
          p3 <- powers3
          p2 <- powers2.takeWhile(_ <= limit - p4 - p3)
      yield p4 + p3 + p2
    solutions.toSet.size
  def solve = solve(50000000)
