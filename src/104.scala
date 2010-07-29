package net.tisue.euler
import Euler._

// Given that F(k) is the first Fibonacci number for which the first nine digits AND the last nine
// digits are 1-9 pandigital, find k.

class Problem104 extends Problem(104, "329468") {
  def solve = {
    import java.math.{BigDecimal,MathContext}
    lazy val fibLastNines:Stream[Int] =
      0 #:: 1 #::
        fibLastNines.zipWith(fibLastNines.tail)((f1,f2) => (f1 + f2) % 1000000000)
    // en.wikipedia.org/wiki/Fibonacci_number#Computation_by_rounding
    def fibFirstNine(k:Int) =
      new BigDecimal((1 + math.sqrt(5d)) / 2)
        .pow(k,MathContext.DECIMAL128)
        .divide(new BigDecimal(math.sqrt(5d)),MathContext.DECIMAL128)
        .toString.view.filter(_ != '.').take(9).map(_.asDigit).toList
    def pandigital(ns:List[Int]) = ns.sum == 45 && ns.product == 362880
    fibLastNines.zipWithIndex.filter{case (f,k) => pandigital(f.digits)}.map(_._2)
      .find(k => pandigital(fibFirstNine(k))).get
  }
}
