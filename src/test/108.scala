package net.tisue.euler
import Primes._

// In the following equation x, y, and n are positive integers:
// 1/x + 1/y = 1/n
// For n = 4 there are exactly three distinct solutions:
// 1/5 + 1/20 = 1/4
// 1/6 + 1/12 = 1/4
// 1/8 + 1/ 8 = 1/4
// What is the least value of n for which the number of distinct solutions exceeds one thousand?

// I wrote some brute force code to count the solutions for small n, then looked up the resulting
// sequence and found it is integer sequence A018892.  The A018892 page gives an easy formula based
// on decomposing n into prime factors, so let's just go ahead and use that.  Runtime is under 2
// seconds.

// A018892 is closely related to A046079 which is the "number of ways in which n can be the leg
// (other than the hypotenuse) of a primitive or nonprimitive right triangle."  So another
// possibility would be to use the pythagorean triple code from problem 86.

class Problem108 extends Problem(108, "180180") {
  // thank you On-Line Encyclopedia of Integer Sequences!
  def A018892(n: Int) =
    (factorCounts(n).map(_ * 2 + 1).product + 1) / 2
  def solve =
    Stream.from(2).find(A018892(_) > 1000).get
}
