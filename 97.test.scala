package net.tisue.euler

// Find the last ten digits of 28433 x 2 ^ 7830457 + 1.

class Problem97 extends Problem(97, "8739992577"):
  def lastTen(n: Long) = n % 10000000000L
  def solve =
    def next(n: Long) = lastTen(2 * n)
    val bigPowerOfTwo = Iterator.iterate(1L)(next).drop(7830457).next()
    lastTen(28433 * bigPowerOfTwo + 1)
