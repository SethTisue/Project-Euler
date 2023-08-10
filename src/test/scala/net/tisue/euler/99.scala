package net.tisue.euler

// Using a 22K text file containing one thousand lines with a base/exponent pair on each line,
// determine which line number has the greatest numerical value.

import collection.immutable.Vector

class Problem99 extends Problem(99, "709") {
  val pairs: Vector[(Int, Int)] =
    io.Source.fromResource("99.txt").getLines()
      .collect{case s"$a,$b" => (a.toInt, b.toInt)}
      .to(Vector)
  def magnitude(index: Int) = {
    val (a, b) = pairs(index)
    math.log(a) * b
  }
  def solve = {
    import Ordering.Double.TotalOrdering
    1 + pairs.indices.maxBy(magnitude)
  }
}
