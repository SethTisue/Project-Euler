package net.tisue.euler

// Add all the natural numbers below 1000 that are multiples of 3 or 5.

class Problem1 extends Problem(1, "233168"):
  def solve =
    (1 to 999).filter(x => x % 3 == 0 || x % 5 == 0).sum

