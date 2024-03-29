package net.tisue.euler

// What is the greatest product of four numbers in any direction (up, down, left, right, or
// diagonally) in the 20x20 grid?

class Problem11 extends Problem(11, "70600674"):
  def solve =
    val a = io.Source.fromResource("11.txt").getLines.map(_.trim.split(" ").map(_.toInt)).toArray
    def inBounds(i: Int) = i >= 0 && i < a.size
    val directions = List((1, 0), (1, 1), (1, -1), (0, 1))
    val products =
      for
        i <- 0 until a.size
        j <- 0 until a.size // try all starting points
        case (xdir, ydir) <- directions
        coords =
          (0 to 3).map(distance =>
            (i + distance * xdir, j + distance * ydir)) // compute coords of four numbers
        if coords.forall: (i, j) => // are all coords in bounds?
          inBounds(i) && inBounds(j)
        product = coords.map(a(_)(_)).product // compute product
      yield product
    products.max
