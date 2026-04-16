package net.tisue.euler

// Find the sum of all numbers equal to the sum of the factorial of their digits (example: 145).

class Problem34 extends Problem(34, "40730"):
  def solve =
    def fact(n: Int): Int = (2 to n).product
    def isSolution(n: Int) =
      n == n.toString.map(c => fact(c.asDigit)).sum
    (10 to 999999).filter(isSolution).sum
