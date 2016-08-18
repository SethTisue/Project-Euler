package net.tisue.euler
import Primes._

// Like problem 108, but we have to be smarter.

// We are asked to find the first solution n for which A018892(n) exceeds 4,000,000.  Empirically, the
// factorCounts values which reach new highs for A018892(n) look like:
// (1)
// (2)
// (1, 1)
// (2, 1)
// (3, 1)
// (1, 1, 1)
// (2, 1, 1)
// (3, 1, 1)
// (2, 2, 1)
// (1, 1, 1, 1)
// (3, 2, 1)
// (2, 1, 1, 1)
// (3, 1, 1, 1)
// (2, 2, 1, 1)
// And so on.  The factor counts are always decreasing, which is another way of saying
// that these are the products of primorials (en.wikipedia.org/wiki/Primorials).

class Problem110 extends Problem(110, "9350130049860600") {
  // thank you On-Line Encyclopedia of Integer Sequences!
  def A018892(n: BigInt) =
    (factorCounts(n).map(_ * 2 + 1).product + 1) / 2
  def partitions(n: Int): List[List[Int]] = {
    def recurse(n: Int, max: Int): List[List[Int]] =
      if(n == 0)
        List(Nil)
      else (1 to (n min max)).toList
             .flatMap(n1 => recurse(n - n1, n1 min max)
             .map(n1 :: _))
    recurse(n, n)
  }
  def primorialsWithNFactors(n: Int) = {
    def expand(f: Int, k: Int) = BigInt(primes(k)).pow(f)
    partitions(n).map(_.zipWithIndex.map(Function.tupled(expand _)).product)
  }
  val threshold = 4000000
  // empirically, 18 is high enough to get the answer.  even using a much larger limit like 40
  // takes under 10 seconds, so I think I'll just settle for using an empirically found limit.
  def solve = (1 to 18)
                .flatMap(primorialsWithNFactors)
                .sorted
                .dropWhile(A018892(_) <= threshold)
                .head
}
