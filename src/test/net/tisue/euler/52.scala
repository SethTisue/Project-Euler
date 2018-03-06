package net.tisue.euler

// It can be seen that the number, 125874, and its double, 251748, contain exactly the same digits,
// but in a different order.
// Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.

object Problem52 extends Problem(52, "142857") {
  def isSolution(x: Int) =
    (1 to 6).map(f => (f * x).digits.sorted).distinct.size == 1
  def solve =
    LazyList(1).find(isSolution).get
}
