package net.tisue.euler

// Find the greatest product of five consecutive digits in this 1000-digit number.

class Problem8 extends Problem(8, "40824") {
  def solve = {
    val input = io.Source.fromFile("dat/8.txt").getLines.map(_.trim).mkString
    input.map(_.asDigit).sliding(5).map(_.product).toSeq.max
  }
}
