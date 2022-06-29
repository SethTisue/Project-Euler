package net.tisue.euler

// Using a text file containing the coordinates of 1000 "random" triangles, find the number of
// triangles for which the interior contains the origin.

// If there's going to be a bunch more problems like this it would be better to
// develop a little library of geometric stuff, but for now we just grind
// out the math.

class Problem102 extends Problem(102, "228"):
  def check(x1: Double, y1: Double, x2: Double, y2: Double, x3: Double, y3: Double) =
    val q = (((x2 - x3) * y2) - ((y2 - y3) * x2)) /
      (((y3 - y2) * x1) - ((x3 - x2) * y1))
    q < 0 || q > 1
  def solve =
    io.Source.fromResource("102.txt").getLines
      .map(_.trim.split(",").toList.map(_.toInt))
      .filter { (_: @unchecked) match
        case Seq(x1, y1, x2, y2, x3, y3) =>
          check(x1, y1, x2, y2, x3, y3) &&
            check(x2, y2, x1, y1, x3, y3) &&
            check(x3, y3, x1, y1, x2, y2)
      }
      .size
