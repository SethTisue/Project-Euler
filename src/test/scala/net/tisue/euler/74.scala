package net.tisue.euler

import Memo.memoize

// The number 145 is well known for the property that the sum of the factorial of its digits is
// equal to 145:  1! + 4! + 5! = 1 + 24 + 120 = 145
// Perhaps less well known is 169, in that it produces the longest chain of numbers that link back to
// 169; it turns out that there are only three such loops that exist:
// 169  363601  1454  169
// 871  45361  871
// 872  45362  872
// Starting with 69 produces a chain of five non-repeating terms, but the longest non-repeating
// chain with a starting number below one million is sixty terms.
// How many chains, with a starting number below one million, contain exactly sixty non-repeating
// terms?

// My brute force solution ran in under 30 seconds but used a huge amount of heap.  So we optimize
// by working in two passes.  During the first pass, we only consider numbers whose non-zero digits
// are in ascending order.  During the second pass, we check all the permutations of the numbers
// that survived the first pass.  (Runs in 4 seconds.)

// This got uglier when I optimized it.  It could use another round of work to make it more elegant
// again.

class Problem74 extends Problem(74, "402"):
  def fact(n: Int): Int = (2 to n).product
  def next(n: Int): Int = n.digits.map(fact).sum
  val chain: Int => LazyList[Int] =
    memoize(n => n #:: chain(next(n)))
  def isSolution(n: Int) = chain(n).take(60).distinct.size == 60
  def solve =
    val candidates =
      for d1 <- 0 to 9; d2 <- 0 :: (d1 to 9).toList; d3 <- 0 :: (d2 to 9).toList
          d4 <- 0 :: (d3 to 9).toList; d5 <- 0 :: (d4 to 9).toList; d6 <- 0 :: (d5 to 9).toList
      yield List(d1, d2, d3, d4, d5, d6).reduceLeft(_ * 10 + _)
    val survivors = candidates.filter(isSolution)
    val permutations =
      for c <- survivors
          n <- c.toString.toList.zipWithIndex.permutations.map(_.map(_._1).mkString.toInt)
      yield n
    permutations.distinct.count(isSolution)
