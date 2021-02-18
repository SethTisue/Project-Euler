package net.tisue.euler

import Primes.*

// The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is
// unusual in two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers
// are permutations of one another.
// There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this
// property, but there is one other 4-digit increasing sequence.
// What 12-digit number do you form by concatenating the three terms in this sequence?

class Problem49 extends Problem(49, "296962999629"):
  val eligibles = primesBelow(10000).dropWhile(_ < 1000).toSet
  def sameDigits(n1: Int, n2: Int) =
    n1.digits.sorted == n2.digits.sorted
  val solutions = for p1 <- eligibles; p2 <- eligibles
                      if p2 > p1 && sameDigits(p1, p2)
                      p3 = p2 * 2 - p1
                      if eligibles.contains(p3) && sameDigits(p2, p3)
                  yield List(p1, p2, p3).mkString
  def solve =
    solutions.find(_ != "148748178147").get

