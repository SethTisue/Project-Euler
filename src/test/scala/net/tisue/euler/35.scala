package net.tisue.euler
import Primes.*

// The number, 197, is called a circular prime because all rotations
// of the digits: 197, 971, and 719, are themselves prime.
// How many circular primes are there below one million?

class Problem35 extends Problem(35, "55"):
  def solve =
    def rotations[A](xs: Seq[A]): Seq[Seq[A]] =
      (0 until xs.size).map(n => xs.drop(n) ++ xs.take(n))
    val primes = primesBelow(1000000).toSet
    def isCircular(n: Int) =
      rotations(n.toString).map(_.mkString.toInt).forall(primes(_))
    primes.count(isCircular)
