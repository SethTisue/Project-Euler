package net.tisue.euler

// For the first 100 natural numbers, find the total of the digital sums of the first 100
// decimal digits for all the irrational square roots.

// The continued fraction & convergents code from problem 66 was much too
// slow for this problem so I used this which is much simpler anyway:
// http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Babylonian_method

// If I understood the whole BigDecimal math context/precision stuff better, I could
// probably avoid dropping down to java.math.BigDecimal, especially now that
// lampsvn.epfl.ch/trac/scala/ticket/1812 is fixed in 2.9, but I don't feel like
// messing with it right now - ST 3/31/11

import java.math.{RoundingMode => J}
import scala.math.BigDecimal.{RoundingMode => S}

class Problem80 extends Problem(80, "40886") {
  val digits = 100
  def isSquare(n: Int) = { val r = math.sqrt(n).toInt; r * r == n }
  // drop down to java.math.BigDecimal to use API not exposed by the Scala wrapper
  def roundingDivide(d1: BigDecimal, d2: BigDecimal) =
    new BigDecimal(d1.bigDecimal.divide(d2.bigDecimal, digits, J.DOWN))
  def digitalSum(d: BigDecimal) =
    d.toString.view.filter(_.isDigit).take(digits).map(_.asDigit).sum
  def firstDuplicate[A](xs: Seq[A]) =
    xs.zip(xs.tail).find{case (d1, d2) => d1 == d2}.get._1
  def babylonian(n: BigDecimal) =
    Stream.iterate(n){next =>
      ((next + roundingDivide(n, next)) / 2)
        .setScale(digits, S.DOWN)}
  def solve =
    (1 to 100)
      .filter(!isSquare(_))
      .map(BigDecimal(_, java.math.MathContext.UNLIMITED))
      .map(n => digitalSum(firstDuplicate(babylonian(n))))
      .sum
}
