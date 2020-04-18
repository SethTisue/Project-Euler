package net.tisue.euler

// The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and are joined to the
// origin, O(0, 0), to form triangle OPQ.  Given that 0 <= x1, y1, x2, y2 <= 50, how many right
// triangles can be formed?

class Problem91 extends Problem(91, "14234"):
  def square(d: Double) = d * d
  def distance(x1: Double, y1: Double, x2: Double, y2: Double) =
    math.sqrt(square(x2 - x1) + square(y2 - y1))
  def near(d1: Double, d2: Double) =
    math.abs(d1 - d2) < 0.00000000001
  case class Triangle(x1: Int, y1: Int, x2: Int, y2: Int)
  def isSolution(t: Triangle) =
    val List(side0, side1, side2) = {
      import Ordering.Double.TotalOrdering
      List(distance(t.x1, t.y1, t.x2, t.y2),
           distance(t.x1, t.y1, 0, 0),
           distance(t.x2, t.y2, 0, 0))
        .sorted.reverse
    }
    near(square(side0), square(side1) + square(side2)) &&
      !near(side0, side1 + side2)
  def candidates =
    for x1 <- 0 to 50
        y1 <- 0 to 50
        x2 <- x1 to 50
        y2 <- 0 to 50
        if x1 < x2 || y1 < y2
    yield Triangle(x1, y1, x2, y2)
  def solve = candidates.count(isSolution)
