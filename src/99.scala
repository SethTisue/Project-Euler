package net.tisue.euler
import Euler._

// Using a 22K text file containing one thousand lines with a base/exponent pair on each line,
// determine which line number has the greatest numerical value.

class Problem99 extends Problem(99, "709") {
  def solve = {
     val pairs = io.Source.fromFile("dat/99.txt").mkString.split("\n")
      .map(_.split(",").map(_.toInt))
    def magnitude(index:Int) = pairs(index) match { case Array(a,b) => math.log(a) * b }
    1 + maximize(0 until pairs.size)(magnitude)
  }
}
