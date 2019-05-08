package net.tisue.euler

// Let p(n) represent the number of different ways in which n coins can be separated into piles. For
// example, five coins can separated into piles in exactly seven different ways (5, 4-1, 3-2, 3-1-1,
// 2-2-1, 2-1-1-1, 1-1-1-1-1), so p(5)=7.  Find the least n for which p(n) is divisible by one million.

// for background, see http://en.wikipedia.org/wiki/Partition_(number_theory)
// Not really wanting to puzzle out the math on the Wikipedia page, I started with the Basic code at
// http://home.att.net/~numericana/answer/numbers.htm#partitions, converted it to Scala, made sure
// it got the right answer, then rewrote it in functional style.  (The functional code is about
// 1.5x slower.)

// As I realized only after looking at the forum, it's faster to always compute p(n) mod one million
// instead of the full p(n). This works because every p(n) is the sum of previous p(n)'s.)
// I changed my code to include this optimization, without needing to restructure anything.

class Problem78 extends Problem(78, "55374") {
  val memo = collection.mutable.ArrayBuffer[Int](1)
  def p(n: Int) = {
    memo +=
      LazyList.from(1).flatMap(k => List(n - (k * k * 3 - k) / 2,
                                         n - (k * k * 3 + k) / 2))
        .takeWhile(_ >= 0)
        .zip(LazyList(1, 1, -1, -1).circular)
        .map{case (j, sign) => memo(j) * sign}
        .sum % 1000000
    memo.last
  }
  def solve = LazyList.from(1).find(p(_) == 0).get
}
