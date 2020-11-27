package net.tisue.euler

// We shall define an almost equilateral triangle to be a triangle for which two sides are equal and
// the third differs by no more than one unit.  The almost equilateral triangle 5-5-6 has an area of
// 12 square units.  Find the sum of the perimeters of all almost equilateral triangles with
// integral side lengths and area and whose perimeters do not exceed one billion (1,000,000,000).

// Brute force, but runs plenty fast enough (8 seconds).

class Problem94 extends Problem(94, "518408346"):
  def isSquare(n: Long) =
    val r = math.sqrt(n.toDouble).toLong
    r * r == n
  def solve =
    val candidates = 3L to (1000000000L / 6)
    type Filter = PartialFunction[Long, Long]
    val filter1: Filter =
      case n if isSquare(3 * n * n - 4 * n + 1) => 6 * n - 2
    val filter2: Filter =
      case n if isSquare(3 * n * n + 4 * n + 1) => 6 * n + 2
    candidates.collect(filter1).sum + candidates.collect(filter2).sum
