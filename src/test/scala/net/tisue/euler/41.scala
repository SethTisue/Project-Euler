package net.tisue.euler
import Primes._

// We shall say that an n-digit number is pandigital if it makes use
// of all the digits 1 to n exactly once. For example, 2143 is a
// 4-digit pandigital and is also prime.
// What is the largest n-digit pandigital prime that exists?

// It's not necessary to check 8 and 9 digit pandigitals, since
// they are all divisible by 3.

class Problem41 extends Problem(41, "7652413") {
  def solve =
    (1 to 7).permutations.map(_.mkString.toInt).filter(isPrime).max
}
