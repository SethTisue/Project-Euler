package net.tisue.euler

// I wrote brute force code, then looked at the answer for low numbers and the pattern was obvious
// and easy to convert to a closed form solution.  But I don't know why it works.

class Problem120 extends Problem(120, "333082500") {
  def rMax(a: Int) = 2 * a * ((a - 1) / 2)
  def solve = (3 to 1000).map(rMax).sum
}
