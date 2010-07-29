package net.tisue.euler
import Euler._

// We shall define an almost equilateral triangle to be a triangle for which two sides are equal and
// the third differs by no more than one unit.  The almost equilateral triangle 5-5-6 has an area of
// 12 square units.  Find the sum of the perimeters of all almost equilateral triangles with
// integral side lengths and area and whose perimeters do not exceed one billion (1,000,000,000).

// This is a dumb brute force solution but runs plenty fast enough (10 seconds).

class Problem94 extends Problem(94, "518408346") {
  def solve = {
    def isSquare(n: Long) = { val r = math.sqrt(n).toLong; r * r == n }
    var n = 3L
    var result = 0L
    while(6 * n <= 1000000000L) {
      if(isSquare(3 * n * n - 4 * n + 1))
        result += 6 * n - 2
      if(isSquare(3 * n * n + 4 * n + 1))
        result += 6 * n + 2
      n += 1
    }
    result
  }
}
