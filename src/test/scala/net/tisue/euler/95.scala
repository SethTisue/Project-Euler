package net.tisue.euler

import Primes._
import Memo.memoize

// The sum of the proper divisors of 220 is 284, and vice versa.  220 and 284 are called an amicable
// pair.  Less well known are longer chains. For example, starting with 12496, we form an
// amicable chain of five numbers: 12496 14288 15472 14536 14264 (12496 ...)
// Find the smallest member of the longest amicable chain with no element exceeding one million.

class Problem95 extends Problem(95, "14316"):
  // mathworld.wolfram.com/DivisorFunction.html
  def properDivisorSum(n: Int): Int =
    if n == 1 then 1
    else
      factors(n)
        .group
        .map:
          fs =>
            val factor = fs.head.toLong
            val exponent = fs.size
            (List.fill(exponent + 1)(factor).product - 1) / (factor - 1)
        .product.toInt - n
  val chain: Int => LazyList[Int] = memoize(n =>
    n #:: chain(properDivisorSum(n)))
  // This part isn't very elegant. I'm not sure how to do better.
  def cycle(ns: LazyList[Int]): List[Int] =
    def recurse(ns: LazyList[Int], seen: List[Int]): List[Int] =
      ns match
        case n #:: ns =>
          if seen.contains(n) then
            n :: seen.takeWhile(_ != n).reverse
          else
            recurse(ns, n :: seen)
        case _ => Nil
    recurse(ns, Nil)
  def solve =
    val longest =
      (2 to 1000000).maxBy(n => cycle(chain(n).takeWhile(_ <= 1000000)).size)
    cycle(chain(longest)).min
