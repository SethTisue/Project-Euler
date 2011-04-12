package net.tisue.euler
import Euler._
import Primes._

// The radical of n, rad(n), is the product of distinct prime factors of n. For example, 504 = 2^3 x
// 3^2 x 7, so rad(504) = 2 x 3 x 7 = 42.  If rad(n) is sorted for 1 <= n <= 100000, find E(10000).

// Not optimized, but plenty fast enough (5 seconds) on this problem size.

class Problem124 extends Problem(124, "21417") {
  def solve = {
    def rad(n:Int) = if(n == 1) 1 else factors(n).toSet.product
    def solve(n:Int,limit:Int) =
      util.Sorting.stableSort(1 to limit,rad _).apply(n - 1)
    solve(10000,100000)
  }
}
