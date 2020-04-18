package net.tisue.euler

// Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.

class Problem48 extends Problem(48, "9110846700"):
  def pow(n: BigInt, p: Int) = List.fill(p)(n).product
  def solve =
    (1 to 1000).map(n => pow(n, n)).sum % 10000000000L

