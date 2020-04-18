package net.tisue.euler

// By counting carefully it can be seen that a rectangular grid
// measuring 3 by 2 contains eighteen rectangles: Although there
// exists no rectangular grid that contains exactly two million
// rectangles, find the area of the grid with the nearest solution.

// Analytically, 1200 is an upper bound on the solution since
// a 2x1200 grid has over 2 million rectangles.

class Problem85 extends Problem(85, "2772"):
  def tri(n: Int) = n * (n + 1) / 2
  def rectangles(w: Int, h: Int) = tri(w) * tri(h)
  val candidates =
    for w <- 2 to 1200
        h <- w to 1200
    yield (w, h)
  def closeness(w: Int, h: Int) =
    math.abs(rectangles(w, h) - 2000000)
  def solve =
    val (w, h) = candidates.minBy((closeness _).tupled)
    w * h
