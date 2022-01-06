package net.tisue.euler

// How many n-digit positive integers exist which are also an nth power?

class Problem63 extends Problem(63, "49"):
  def solutions(n: Int): LazyList[BigInt] =
    LazyList.from(1)
      .map(BigInt(_).pow(n))
      .dropWhile(_.toString.size < n)
      .takeWhile(_.toString.size == n)
  def solve =
    (1 to 25).flatMap(solutions(_)).size
