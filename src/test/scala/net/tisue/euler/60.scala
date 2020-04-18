package net.tisue.euler

import Primes._

// Find the lowest sum for a set of five primes from which any two primes concatenate to produce
// another prime.

// The limit of 10,000 was arrived at by trial and error.  Most people on the problem's forum seem
// to have done the same, so I don't feel all that bad about it.  This solution runs in 53 seconds
// including sieving time (many forum posters don't count sieving time).  Initially it took
// more like 80 but when I saw pinkpuppy's Scala solution I realized my algorithm could be
// improved by passing already-filtered prime lists in my recursive calls.
// pinkpuppy says his Scala solution runs in 0.53 seconds, but I think that's just because
// he assumes the first solution that pops out has the minimum sum, rather than continuing
// to search the rest of the space as my code does.

class Problem60 extends Problem(60, "26033"):
  def concat(n1: Int, n2: Int) =
    s"$n1$n2".toInt
  def isPair(n1: Int, n2: Int) =
    isPrime(concat(n1, n2)) && isPrime(concat(n2, n1))
  def isSolution(n: Int, ns: List[Int]) =
    ns.forall(isPair(n, _)) // assumes ns was already checked
  def solutions(n: Int, primes: List[Int]): List[List[Int]] =
    if n == 1 then
      primes.map(List(_))
    else for p <- primes
             filtered = primes.dropWhile(_ <= p).filter(isPair(p, _))
             soln <- solutions(n - 1, filtered)
         yield p :: soln
  def solve =
    solutions(5, primesBelow(10000)).minBy(_.sum).sum

