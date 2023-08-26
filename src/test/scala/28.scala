package net.tisue.euler

// What is the sum of both diagonals in a 1001 by 1001 spiral?

class Problem28 extends Problem(28, "669171001"):
  def solve = 1 + (3 to 1001 by 2).map(n => 4 * n * n - (n - 1) * 6).sum
