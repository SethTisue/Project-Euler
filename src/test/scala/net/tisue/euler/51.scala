package net.tisue.euler

import Primes._

// By replacing the 1st digit of *57, it turns out that six of the possible values: 157, 257, 457,
// 557, 757, and 857, are all prime.
// By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the
// first example having seven primes, yielding the family: 56003, 56113, 56333, 56443, 56663, 56773,
// and 56993. Consequently 56003, being the first member of this family, is the smallest prime with
// this property.
// Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits)
// with the same digit, is part of an eight prime value family.

class Problem51 extends Problem(51, "121313"):
  def solve =
    def nDigitPrimes(n: Int) =
      val lowerLimit = List.fill(n - 1)(10).product
      primesBelow(lowerLimit * 10).dropWhile(_ < lowerLimit).toSet
    def templates(n: Int): List[String] =
      if n == 0 then
        List("")
      else
        templates(n - 1).flatMap(template => "0123456789*".map(_.toString + template))
    val solutions =
      for numDigits <- LazyList.from(1)
          primes = nDigitPrimes(numDigits)
          template <- templates(numDigits)
          if template(0) != '0' && template.contains('*')
      yield (0 to 9).map(d => template.replaceAll("\\*", d.toString).toInt).filter(primes.contains(_))
    solutions.find(_.size == 8).get.head
