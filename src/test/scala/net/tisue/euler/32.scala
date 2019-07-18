package net.tisue.euler

// Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1
// through 9 pandigital, for example 39 x 186 = 7254.

// This is pretty clumsy, but oh well...

class Problem32 extends Problem(32, "45228") {
  def makeNum(digits: Seq[Int]) =
    digits.reduceLeft((a, b) => 10 * a + b)
  val products =
    for {
      digits <- (1 to 9).permutations
      len <- List(1, 2)
      n1 = makeNum(digits.take(len))
      n2 = makeNum(digits.slice(len, 5))
      p = makeNum(digits.drop(5))
      if n1 * n2 == p
    } yield p
  def solve = products.toSet.sum
}
