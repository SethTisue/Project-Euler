package net.tisue.euler
import Primes._

// The number, 1406357289, is a 0 to 9 pandigital number because it is
// made up of each of the digits 0 to 9 in some order, but it also has a
// rather interesting sub-string divisibility property.
// Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way,
// we note the following:
// d2d3d4=406 is divisible by 2
// d3d4d5=063 is divisible by 3
// d4d5d6=635 is divisible by 5
// d5d6d7=357 is divisible by 7
// d6d7d8=572 is divisible by 11
// d7d8d9=728 is divisible by 13
// d8d9d10=289 is divisible by 17
// Find the sum of all 0 to 9 pandigital numbers with this property.

class Problem43 extends Problem(43, "16695334890") {
  def solve = {
    def missingDigits(x: String) =
      ('0' to '9').toList.filterNot(x.contains(_))
    def hasProperty(x: String) =
      x.size < 3 || x.take(3).toInt % primes(9 - x.size) == 0
    def recurse(x: String): List[String] =
      if(x.size == 10) List(x)
      else if(!hasProperty(x)) Nil
      else missingDigits(x).flatMap(d => recurse(d.toString + x))
    recurse("").filter(_(0) != '0').map(BigInt(_)).sum
  }
}
