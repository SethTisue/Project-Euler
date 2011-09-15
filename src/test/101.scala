package net.tisue.euler

// There are a lot of different ways you can solve this.
// Cramer's Rule is one, Pascal's triangle is another.
// Instead of anything fancy I used the method of successive differences which
// is taught to schoolchildren and which is easy to do with pencil and paper.

// The "diagonal" method returns the first numbers in the rows of successive differences.
// From those we can re-extrapolate the sequence.

// generalbaguette's Haskell solution is mostly like mine, but more concisely and elegantly
// expressed.  I'm not sure whether I'm more happy that we used a similar solution plan, or more sad
// that his is better.

class Problem101 extends Problem(101, "37076114526") {
  def differences(ns: Seq[BigInt]) =
    (ns.tail, ns).zipped.map(_ - _)
  def diagonal(ns: Seq[BigInt]) =
    Iterator.iterate(ns)(differences)
      .takeWhile(_.nonEmpty)
      .map(_.head)
      .toList.reverse
  def extrapolate(ns: Seq[BigInt]) = {
    def addDifferences(diffs: Stream[BigInt], init: BigInt): Stream[BigInt] =
      init #:: addDifferences(diffs.tail, init + diffs.head)
    diagonal(ns).foldLeft(Stream(BigInt(0)).circular)(addDifferences)
  }
  def mismatch[T](master: Seq[T], copy: Seq[T]) =
    (master zip copy).find(p => p._1 != p._2).get._2
  def solve = {
    val coefficients = List[BigInt](1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1)
    val sequence = Stream.from(1).map(n => coefficients.reduceLeft(_ * n + _))
    sequence.take(coefficients.size - 1)  // subtract one so there's always a mismatch
      .inits
      .map(extrapolate)
      .map(mismatch(sequence, _))
      .sum
  }
}
