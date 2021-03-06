package net.tisue.euler

import Primes.*

// What is the first value which can be written as the sum of primes in over 5000 different ways?

// mathworld.wolfram.com/PrimePartition.html
// mathworld.wolfram.com/EulerTransform.html

class Problem77 extends Problem(77, "71"):
  def solve =
    def factorSum(n: Int) =
      if n < 2 then 0 else factors(n).toSet.sum
    val memo = collection.mutable.ArrayBuffer[Int]()
    memo ++= Seq(0, 0)
    def partitionCount(n: Int): Int =
      memo += ((1 until n).map(k => factorSum(k) * memo(n - k)).sum + factorSum(n)) / n
      memo.last
    LazyList.from(2).find(partitionCount(_) > 5000).get
