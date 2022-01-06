package net.tisue.euler

// If p is the perimeter of a right angle triangle with integral
// length sides, {a,b,c}, there are exactly three solutions for p =
// 120: {20,48,52}, {24,45,51}, {30,40,50}
// For which value of p < 1000, is the number of solutions maximised?

class Problem39 extends Problem(39, "840"):
  def solve =
    def triangles(p: Int) =
      for
        a <- 1 to p / 2
        b <- 1 to a - 1
        c = p - a - b
        if a * a + b * b == c * c
      yield (a, b, c)
    (1 until 1000).maxBy(triangles(_).size)
