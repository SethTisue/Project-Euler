package net.tisue.euler

// Find the minimal path sum in matrix.txt (from problem 81) by starting in any cell in the left
// column and finishing in any cell in the right column, and only moving up, down, and right.

// This solution is totally imperative.

object Problem82 extends Problem(82, "260324") {
  def solve = {
     val matrix = io.Source.fromResource("81.txt")
      .getLines.map(_.trim.split(",").map(_.toInt)).toArray
    val range = 0 until matrix.size
    // This is a little confusing because we address the matrix using (y, x)
    // rather than (x,y), but oh well
    for{x <- range.reverse.drop(1); y <- range}
      matrix(y)(x) = {
        val down = if(y == 0) Integer.MAX_VALUE
                 else matrix(y-1)(x) + matrix(y)(x)
        val up =
          (y until matrix.size).map(y2 =>
            (y to y2).map(matrix(_)(x)).sum + matrix(y2)(x + 1))
        down min up.min
      }
    range.map(matrix(_)(0)).min
  }
}
