package net.tisue.euler
import Euler._

// How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for
// d <= 10,000?

class Problem73 extends Problem(73, "5066251") {
  def solutions = 
    for{d <- 2 to 10000
        n <- math.ceil(d.toDouble / 3).toInt to math.floor(d.toDouble / 2).toInt
        if (n gcd d) == 1}
    yield (n, d)
  def solve = solutions.size - 2
}
