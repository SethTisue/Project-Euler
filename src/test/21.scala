package net.tisue.euler

// Evaluate the sum of all the amicable numbers under 10000.
// (In a pair of amicable numbers, each number is equal to the sum
// of the other's divisors including 1.)

// Note: There is a slight ambiguity in the problem statement; it
// doesn't say whether if a pair exists with one number below 10000
// and one above, the lower number should be included in a sum.  As it
// happens there is no such pair so it doesn't matter in this case,
// but for a different bound besides 10000 it might matter.  Anyway,
// it seems reasonable to interpret the problem such that both numbers
// must be below the bound, and under that interpretation the code
// below is correct in general.

class Problem21 extends Problem(21, "31626") {
  val divisorSums =
    Stream.from(0).map(n =>
      (1 to n / 2).filter(n % _ == 0).sum)
  val solutions =
    (2 until 10000).flatMap{n => val sum = divisorSums(n)
                                 if(n > sum && n == divisorSums(sum))
                                   List(sum, n)
                                 else Nil}
  def solve = solutions.sum
}
