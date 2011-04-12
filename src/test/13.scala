package net.tisue.euler
import Euler._

// Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.

class Problem13 extends Problem(13, "5537376230") {
  def solve = {
    val inputs = io.Source.fromFile("dat/13.txt").getLines.map(line => BigInt(line.trim)).toList
    inputs.sum.toString.slice(0, 10).mkString
  }
}
