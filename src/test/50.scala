package net.tisue.euler
import Primes._
import annotation.tailrec

// The prime 41, can be written as the sum of six consecutive primes:
//   41 = 2 + 3 + 5 + 7 + 11 + 13
// This is the longest sum of consecutive primes that adds to a prime below one-hundred.  The
// longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and
// is equal to 953.  Which prime, below one-million, can be written as the sum of the most
// consecutive primes?

// This is ugly.  Originally it was imperative code with several vars.  I have a self-imposed rule
// not to use vars, so I replaced the loops with recursion, resulting in the code below, but it's
// actually less elegant than the imperative version, I think.

class Problem50 extends Problem(50, "997651") {
  def solve(ps: Stream[Int], longest: List[Int]): Int = {
    if(ps.take(longest.size + 1).sum >= 1000000)
      longest.sum
    else {
      @tailrec def solve2(len: Int, newLongest: List[Int]): List[Int] = {
        if(ps.take(len).sum >= 1000000) newLongest
        else solve2(len + 1,
                    if(isPrime(ps.take(len).sum)) ps.take(len).toList
                    else newLongest)
      }
      solve(ps.tail, solve2(longest.size + 1, longest))
    }
  }
  def solve = solve(primes, Nil)
}

// and an imperative version:
class Problem50i extends Problem(50, "997651") {
  def solve = {
    var ps = primes
    var longest: List[Int] = Nil
    while(ps.take(longest.size + 1).sum < 1000000) {
      var len = longest.size + 1
      while(ps.take(len).sum < 1000000) {
        if(isPrime(ps.take(len).sum))
          longest = ps.take(len).toList
        len += 1
      }
      ps = ps.tail
    }
    longest.sum
  }
}

