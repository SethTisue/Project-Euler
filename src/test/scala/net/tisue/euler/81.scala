package net.tisue.euler

// Find the minimal path sum in matrix.txt from the top left to the
// bottom right by only moving right and down.

// This solution is totally imperative.

class Problem81 extends Problem(81, "427337") {
  def solve = {
     val matrix = io.Source.fromResource("81.txt").mkString.trim.split("\n")
      .map(_.split(",").map(_.toInt))
    val range = 1 until matrix.size
    for{x <- range} matrix(x)(0) += matrix(x - 1)(0)
    for{y <- range} matrix(0)(y) += matrix(0)(y - 1)
    for{x <- range; y <- range}
      matrix(x)(y) += matrix(x - 1)(y) min matrix(x)(y - 1)
    matrix.last.last
  }
}
