package net.tisue.euler

// This is actually faster if you don't memoize.

class Problem14 extends Problem(14, "837799") {
  def next(n: Long) =
    if(n % 2 == 0) n / 2
    else 3 * n + 1
  def chainLength(n: Long): Int =
    Iterator.iterate(n)(next).takeWhile(_ != 1).size
  def solve =
    (3L until 1000000 by 2).maxBy(chainLength)
}
