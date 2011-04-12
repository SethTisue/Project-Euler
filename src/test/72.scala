package net.tisue.euler
import Euler._
import Primes._

// How many elements would be contained in the set of reduced proper fractions with denominator <=
// 1,000,000?  (for denominator <= 8, there are 21)

// This is basically asking us for a sum of totients. See
//   http://en.wikipedia.org/wiki/Euler%27s_totient_function
// (The "Other formulas involving Euler's function" section of this page has a formula for computing
// the sum of the totients using the Moebius function, but the following approach is easier to
// understand and plenty fast enough.)

// I didn't look at this until after I wrote the code below:
//   http://en.wikipedia.org/wiki/Farey_sequence
// but it might be useful if there are more problems later on this theme.

class Problem72 extends Problem(72, "303963552391") {
  def solve = {
    def factors(n:Int):List[Int] =
      if(isSievedPrime(n)) List(n)
      else { val f = primes.find(n % _ == 0).get ; f :: factors(n / f) }
    def totient(n:Int) = factors(n).toSet.foldLeft(n.toLong)((t,f) => t * (f - 1) / f)
    def solve(limit:Int) = (2 to limit).map(totient).sum
    solve(1000000)
  }
}
