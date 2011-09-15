package net.tisue.euler

// The points P (x1, y1) and Q (x2, y2) are plotted at integer co-ordinates and are joined to the
// origin, O(0,0), to form triangle OPQ.  Given that 0 <= x1, y1, x2, y2 <= 50, how many right
// triangles can be formed?

class Problem91 extends Problem(91, "14234") {
  def solve = {
    import math._
    def square(d:Double) = d * d
    def distance(x1:Double,y1:Double,x2:Double,y2:Double) = sqrt(square(x2 - x1) + square(y2 - y1))
    val infinitesimal = 0.00000000001
    def close(d1:Double,d2:Double) = abs(d1 - d2) < infinitesimal
    def isSolution(x1:Int,y1:Int,x2:Int,y2:Int) = {
      val List(side0,side1,side2) =
        List(distance(x1,y1,x2,y2),distance(x1,y1,0,0),distance(x2,y2,0,0)).sorted.reverse
      !close(side0,side1 + side2) && close(square(side0),square(side1) + square(side2))
    }
    (for{x1 <- 0 to 50; y1 <- 0 to 50; x2 <- x1 to 50; y2 <- 0 to 50
         if (x1 < x2 || y1 < y2) && isSolution(x1,y1,x2,y2)}
     yield true).size
  }
}
