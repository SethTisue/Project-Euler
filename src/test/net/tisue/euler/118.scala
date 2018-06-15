package net.tisue.euler
import Primes._

// 6 seconds!

class Problem118 extends Problem(118, "44680") {
  // not that happy with the clarity of this function, but oh well
  def partitions[T](ns: List[T]): List[List[List[T]]] =
    ns match {
      case Nil => List(Nil)
      case n :: ns =>
        val recurse = partitions(ns)
        recurse.map(r => List(n) :: r) :::
          (for(r <- recurse; m <- r.indices)
           yield r.patch(m, List(n :: r(m)), 1))
    }
  // we need to test lots of small numbers but only a few large numbers,
  // so we use two different prime tests depending on the size of n
  def fastPrimeTest(n: Int) =
    if(n < 10000000) isSievedPrime(n)
    else isPrime(n)
  def countPrimePermutations(n: List[Int]) =
    n.permutations.map(_.mkString.toInt).count(fastPrimeTest)
  def countSolutions(ns: List[List[Int]]): Int =
    // this quick check on the sum of digits prunes the search space a lot
    if(ns.exists(x => x.size > 1 && x.sum % 3 == 0)) 0
    else ns.foldLeft(1){case (product, n) =>
      val p = countPrimePermutations(n)
      if(p == 0) return 0  // early exit for speed
      else product * p
    }
  def solve =
    partitions((1 to 9).toList).map(countSolutions).sum
}
