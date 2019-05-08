package net.tisue.euler
import Primes._

// A natural number, N, that can be written as the sum and product of a given set of at least two
// natural numbers, {a1, a2, ... , ak} is called a product-sum number: N = a1 + a2 + ... + ak = a1
// * a2 * ... * ak.
// For a given set of size, k, we shall call the smallest N with this property a minimal product-sum
// number. The minimal product-sum numbers for sets of size, k = 2, 3, 4, 5, and 6 are as follows.
// k=2: 4 = 2  2 = 2 + 2
// k=3: 6 = 1  2  3 = 1 + 2 + 3
// k=4: 8 = 1  1  2  4 = 1 + 1 + 2 + 4
// k=5: 8 = 1  1  2  2  2 = 1 + 1 + 2 + 2 + 2
// k=6: 12 = 1  1  1  1  2  6 = 1 + 1 + 1 + 1 + 2 + 6
// Hence for 2 <= k <= 6, the sum of all the minimal product-sum numbers is 4+6+8+12 = 30; note that 8
// is only counted once in the sum.  And as the complete set of minimal product-sum numbers for
// 2 <= k <=12 is {4, 6, 8, 12, 15, 16}, the sum is 61.
// What is the sum of all the minimal product-sum numbers for 2 <= k <= 12000?

// This runs in 8.2 seconds.  It's not as elegant as I'd like, but I'm just so happy
// to have something that runs so fast.  We use a rolling cache, "stream", that remembers
// already-computed values, but we also drop no-longer needed entries, using
// assignment (stream = stream.dropWhile(...)).

class Problem88 extends Problem(88, "7587457") {
  val divisors = LazyList.from(0).map(n => (2 to n).filter(n % _ == 0).toList)
  def factorizations(n: Int) = {
    def helper(n: Int, ceiling: Int): List[List[Int]] =
      if(n == 1) List(Nil)
      else
        for{d <- divisors(n).takeWhile(_ <= ceiling)
            f <- helper(n / d, d)}
        yield d :: f
    helper(n, n)
  }
  def getK(factors: List[Int]) = factors.size + factors.product - factors.sum
  var stream = LazyList.from(2).filter(!isPrime(_)).map(n => (n, factorizations(n)))
  def solve(limit: Int) =
    (for{k <- (2 to limit).toList
         _ = (stream = stream.dropWhile(_._1 < k))
         n = stream.find(_._2.exists(fs => getK(fs) == k)).get._1}
     yield n).distinct.sum
  def solve = solve(12000)
}
