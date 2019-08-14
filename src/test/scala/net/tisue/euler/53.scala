package net.tisue.euler

// How many values of nCr, for 1 <= n <= 100, are greater than one million?

object Problem53 extends Problem(53, "4075") {
  def fact(n: Int): BigInt = (BigInt(1) to n).product
  def combinations(n: Int, r: Int) = fact(n) / (fact(r) * fact(n - r))
  def solve =
    (for{n <- 1 to 100; r <- 1 to n}
     yield combinations(n, r))
    .count(_ > 1000000)
}
