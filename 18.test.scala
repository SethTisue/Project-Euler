package net.tisue.euler

// Find the maximum sum travelling from the top of the triangle to the base.

// Brute force solution.  The problem has a hint that says something cleverer is possible
// (and Problem 67 is the same but with a triangle too big to solve by brute force).
// I guess I'll cross that bridge when I come to it...

class Problem18 extends Problem(18, "1074"):
  def solve =
    def recurse(triangle: List[List[Int]]): Int =
      if triangle.isEmpty then 0
      else
        triangle.head.head + (recurse(triangle.tail.map(_.init)) max
          recurse(triangle.tail.map(_.tail)))
    val triangle =
      io.Source.fromResource("18.txt").getLines().toList.map(_.trim.split(" ").map(_.toInt).toList)
    recurse(triangle)
