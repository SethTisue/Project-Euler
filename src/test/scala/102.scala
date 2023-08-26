package net.tisue.euler

// Using a text file containing the coordinates of 1000 "random" triangles, find the number of
// triangles for which the interior contains the origin.

// If there's going to be a bunch more problems like this it would be better to
// develop a little library of geometric stuff, but for now we just grind
// out the math.

class Problem102 extends Problem(102, "228"):
  case class Point(x: Double, y: Double)
  case class Triangle(p1: Point, p2: Point, p3: Point)
  def check(t: Triangle): Boolean =
    import t.*
    val q = (((p2.x - p3.x) * p2.y) - ((p2.y - p3.y) * p2.x)) /
      (((p3.y - p2.y) * p1.x) - ((p3.x - p2.x) * p1.y))
    q < 0 || q > 1
  def solve =
    io.Source.fromResource("102.txt").getLines
      .collect:
        case s"$x1,$y1,$x2,$y2,$x3,$y3" =>
          val p1 = Point(x1.toDouble, y1.toDouble)
          val p2 = Point(x2.toDouble, y2.toDouble)
          val p3 = Point(x3.toDouble, y3.toDouble)
          check(Triangle(p1, p2, p3)) &&
            check(Triangle(p2, p1, p3)) &&
            check(Triangle(p3, p1, p2))
      .count(identity)
