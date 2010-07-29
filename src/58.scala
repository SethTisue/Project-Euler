package net.tisue.euler
import Euler._
import Primes._

// Starting with 1 and spiralling anticlockwise in the following way, a square spiral with side
// length 7 is formed.
// 37 36 35 34 33 32 31
// 38 17 16 15 14 13 30
// 39 18  5  4  3 12 29
// 40 19  6  1  2 11 28
// 41 20  7  8  9 10 27
// 42 21 22 23 24 25 26
// 43 44 45 46 47 48 49
// It is interesting to note that the odd squares lie along the bottom right diagonal, but what is
// more interesting is that 8 out of the 13 numbers lying along both diagonals are prime.  If this
// process is continued, what is the side length of the square spiral for which the ratio of primes
// along both diagonals first falls below 10%?

class Problem58 extends Problem(58, "26241") {
  def solve = {
    def next(n: Int, primeCount: Int) =
      (n + 2,
       primeCount + List(n * n + n + 1,
                         n * n + n * 2 + 2,
                         n * n + n * 3 + 3)
                    .count(isPrime))
    def isSolution(n: Int, primeCount: Int) =
      primeCount.toDouble / (2 * n - 1) < 0.10
    Stream.iterate((1,0))((next _).tupled)
      .tail
      .find((isSolution _).tupled)
      .get._1
  }
}
