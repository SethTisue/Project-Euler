package net.tisue.euler
import Euler._

// The fraction 49/98 is a curious fraction, as an inexperienced
// mathematician in attempting to simplify it may incorrectly believe
// that 49/98 = 4/8, which is correct, is obtained by cancelling the
// 9s.  We shall consider fractions like, 30/50 = 3/5, to be trivial
// examples.  There are exactly four non-trivial examples of this type
// of fraction, less than one in value, and containing two digits in
// the numerator and denominator.  If the product of these four
// fractions is given in its lowest common terms, find the value of
// the denominator.

// This code is excessively general; it would have been easier, I
// think, to iterate over possible individual digits rather than
// iterating over possible two-digit numbers.

class Problem33 extends Problem(33, "100") {
  def solve = {
    val fractions =
      for{a <- 10 to 98
          b <- (a + 1) to 99
          uniqueDigits = (a.toString + b.toString).toSet
          if uniqueDigits.size == 3
          sharedDigit <- uniqueDigits.find(d => a.toString.contains(d) && b.toString.contains(d))
          if sharedDigit != '0'
          newA = a.toString.filter(_ != sharedDigit).mkString.toInt
          newB = b.toString.filter(_ != sharedDigit).mkString.toInt
          if a * newB == b * newA
        } yield (a, b)
    val (a, b) = (fractions.map(_._1).product, fractions.map(_._2).product)
    b / (a gcd b)
  }
}
