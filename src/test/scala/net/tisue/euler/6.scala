package net.tisue.euler

// Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.

class Problem6 extends Problem(6, "25164150") {
  val range = BigInt(1) to 100
  def solve = range.sum.pow(2) - range.map(n => n * n).sum
}
