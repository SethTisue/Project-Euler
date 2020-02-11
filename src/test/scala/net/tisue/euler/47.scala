package net.tisue.euler
import Primes._

// The first two consecutive numbers to have two distinct prime factors are:
//   14 = 2 x 7
//   15 = 3 x 5
// The first three consecutive numbers to have three distinct prime factors are:
//   644 = 2^2 x 7 x 23
//   645 = 3 x 5 x 43
//   646 = 2 x 17 x 19
// Find the first four consecutive integers to have four distinct prime factors. What is the first
// of these numbers?

class Problem47 extends Problem(47, "134043") {
  def solve = {
    def factorSet(n: Int): Set[Int] =
      if(n == 1) Set()
      else {
        val f = primes.find(n % _ == 0).get
        factorSet(n / f) + f
      }
    LazyList.from(1).tails.find(_.take(4).forall(factorSet(_).size == 4)).get.head
  }
}
