package net.tisue.euler

// It is possible to show that the square root of two can be expressed as an infinite continued
// fraction.  sqrt 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...  In the first one-thousand
// expansions, how many fractions contain a numerator with more digits than denominator?

class Problem57 extends Problem(57, "153"):
  def solve =
    LazyList.iterate((BigInt(2), BigInt(1))){case (n, d) => (d + n * 2, n)}
      .drop(1).take(1000)
      .map{case (n, d) => (n - d, d)}
      .count{case (n, d) => n.toString.size > d.toString.size}

