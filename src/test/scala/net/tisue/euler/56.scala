package net.tisue.euler

// Considering natural numbers of the form a ^ b where a, b < 100, what is the maximum digital sum?

object Problem56 extends Problem(56, "972") {
  def digitalSums = for(a <- 1 to 100; b <- 1 to 100)
                    yield BigInt(a).pow(b).digits.sum
  def solve = digitalSums.max
}
