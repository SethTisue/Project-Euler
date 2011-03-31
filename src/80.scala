package net.tisue.euler
import Euler._

// For the first 100 natural numbers, find the total of the digital sums of the first 100
// decimal digits for all the irrational square roots.

// The continued fraction & convergents code from problem 66 was much too
// slow for this problem so I used this which is much simpler anyway:
// http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Babylonian_method

class Problem80 extends Problem(80, "40886") {
  def isSquare(n: Int) = { val r = math.sqrt(n).toInt; r * r == n }
  // drop down to java.math.BigDecimal to use API not exposed by the Scala wrapper
  def roundingDivide(d1: BigDecimal, d2: BigDecimal, digits: Int) =
    new BigDecimal(d1.bigDecimal.divide(d2.bigDecimal, digits, java.math.RoundingMode.DOWN))
  def firstDuplicate[A](xs: Seq[A]) =
    xs.zip(xs.tail).find{case (d1, d2) => d1 == d2}.get._1
  def solve = {
    val digits = 100
    def babylonian(n:Int) =
      firstDuplicate(
        Stream.iterate(BigDecimal(n))(x => ((x + roundingDivide(n, x, digits)) / 2)
          .setScale(digits, BigDecimal.RoundingMode.DOWN)))
    def digitalSum(d: BigDecimal) =
      d.toString.view.filter(_.isDigit).take(digits).map(_.asDigit).sum
    (1 to 100).filter(!isSquare(_)).map(n => digitalSum(babylonian(n))).sum
  }
}
