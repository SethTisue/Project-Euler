package net.tisue.euler

// Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:
// Triangle             Tn=n(n+1)/2             1, 3, 6, 10, 15, ...
// Pentagonal           Pn=n(3n-1)/2            1, 5, 12, 22, 35, ...
// Hexagonal            Hn=n(2n-1)              1, 6, 15, 28, 45, ...
// It can be verified that T285 = P165 = H143 = 40755.
// Find the next triangle number that is also pentagonal and hexagonal.

class Problem45 extends Problem(45, "1533776805"):
  def solve =
    def triangle(n: BigInt) = n * (n + 1) / 2
    def pentagonal(n: BigInt) = n * (n * 3 - 1) / 2
    def hexagonal(n: BigInt) = n * (n * 2 - 1)
    def recurse(n1: BigInt, n2: BigInt, n3: BigInt): LazyList[BigInt] =
      val t = triangle(n1)
      val p = pentagonal(n2)
      val h = hexagonal(n3)
      if t == p && p == h then
        t #:: recurse(n1 + 1, n2 + 1, n3 + 1)
      else if t <= p && p <= h then
        recurse(n1 + 1, n2, n3)
      else if p <= t && t <= h then
        recurse(n1, n2 + 1, n3)
      else
        recurse(n1, n2, n3 + 1)
    recurse(1, 1, 1).dropWhile(_ <= 40755).head
