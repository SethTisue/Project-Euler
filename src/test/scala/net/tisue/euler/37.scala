package net.tisue.euler
import Primes._

// Find the sum of the only eleven primes that are both truncatable from left to right and right to
// left.  (Example: 3797.  379, 37, 3, 797, 97, and 7 are all prime.)

class Problem37 extends Problem(37, "748317") {
  def solve = {
    def isTruncatable(n:Int) =
      (1 until n.toString.size).forall(j => isSievedPrime(n.toString.drop(j).toInt) &&
                                            isSievedPrime(n.toString.take(j).toInt))
    primes.dropWhile(_ < 10).filter(isTruncatable).take(11).sum
  }
}
