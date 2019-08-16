package net.tisue.euler
import Primes._

// Euler's Totient function phi(n) is used to determine the number of numbers less than n which are
// relatively prime to n.  n=6 produces a maximum n/phi(n) for n <= 10.  Find the value of n <=
// 1,000,000 for which n/phi(n) is a maximum.

// I wasn't 100% sure this approach would be guaranteed to produce the right answer, but it does.
// (People on the forum seem confident the algorithm is guaranteed to work.)

object Problem69 extends Problem(69, "510510") {
  def solve =
    primes.scanLeft(1)(_ * _)
      .takeWhile(_ <= 1000000)
      .last
}
