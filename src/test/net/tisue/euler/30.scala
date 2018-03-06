package net.tisue.euler

// Find the sum of all the numbers that can be written as the sum of
// fifth powers of their digits.

object Problem30 extends Problem(30, "443839") {
  def pow(n: Int, exponent: Int) = List.fill(exponent)(n).product
  def isSolution(n: Int) = n == n.digits.map(pow(_, 5)).sum
  def solve = (2 to 999999).filter(isSolution).sum
}
