package net.tisue.euler

// It is possible to write five as a sum in exactly six different ways:
// 4 + 1
// 3 + 2
// 3 + 1 + 1
// 2 + 2 + 1
// 2 + 1 + 1 + 1
// 1 + 1 + 1 + 1 + 1
// How many different ways can one hundred be written as a sum of at least two positive integers?

class Problem76 extends Problem(76, "190569291"):
  def sums(n: Int) =
    val mem = Array.ofDim[Int](n + 1, n + 1)
    def sums(n: Int, limit: Int): Int =
      if mem(n)(limit) == 0 then
        mem(n)(limit) =
          if n < 2 then 1
          else (1 to (limit min n)).map(a1 => sums(n - a1, a1)).sum
      mem(n)(limit)
    sums(n, n - 1)
  def solve = sums(100)
