package net.tisue.euler

// By listing the set of reduced proper fractions for d <= 1,000,000
// in ascending order of size, find the numerator of the fraction
// immediately to the left of 3/7.

class Problem71 extends Problem(71, "428570"):
  import Ordering.Double.TotalOrdering
  def solve =
    (1 to 1000000)
      .map(d => (d * 3 / 7, d))
      .filter { case (n, d) => n * 7 != d * 3 }
      .maxBy { case (n, d) => n.toDouble / d }
      ._1
