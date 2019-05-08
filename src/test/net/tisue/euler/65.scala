package net.tisue.euler

// The important mathematical constant, e, equals
// [2; 1,2,1, 1,4,1, 1,6,1 , ... , 1,2k,1, ...].
// Find the sum of digits in the numerator of the 100th convergent of the continued fraction for e.

// This could also be solved using Problem 66's generalized continued fraction code.

class Problem65 extends Problem(65, "272") {
  // This would be more easily and clearly expressed using some sort of
  // BigRational class, I think.  The recursion is basically a fold
  // and if we didn't have to assemble and reassemble Tuple2s I have a
  // hunch that could be made clearer.
  def calc(xs: List[Int]): (BigInt, BigInt) = xs match {
    case x :: xs => calc(xs) match { case (a, b) => (b + a * x, a) }
    case Nil => (1, 0)
  }
  val series = 2 #:: LazyList.from(1).flatMap(n => LazyList(1, n * 2, 1))
  def solve =
    calc(series.take(100).toList)._1.digits.sum
}
