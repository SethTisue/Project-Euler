package net.tisue.euler

// Using a 22K text file containing one thousand lines with a base/exponent pair on each line,
// determine which line number has the greatest numerical value.

import collection.immutable.Vector

class Problem99 extends Problem(99, "709") {
  val pairs =
    io.Source.fromResource("99.txt").getLines
      .map(_.split(",").map(_.toInt))
      .to[Vector]
  def magnitude(index: Int) =
    pairs(index) match {
      case Array(a, b) => math.log(a) * b
    }
  def solve =
    1 + pairs.indices.maxBy(magnitude)
}
