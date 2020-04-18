package net.tisue.euler

// Find the number of integers 1 < n < 10^7, for which n and n + 1 have the same number of positive
// divisors. For example, 14 has the positive divisors 1, 2, 7, 14 while 15 has 1, 3, 5, 15.

class Problem179 extends Problem(179, "986262") {
  def solve = {
    val max = 10000000
    val counts = Array.ofDim[Int](max + 1)
    for{a <- 2 to max
        b <- a to max by a}
      counts(b) += 1
    (2 to max - 1).count(n => counts(n) == counts(n + 1))
  }
}
