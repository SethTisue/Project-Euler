package net.tisue.euler

// A number whose proper divisors when summed exceed the number is called abundant.  Find the sum of
// all the positive integers which cannot be written as the sum of two abundant numbers.  (It can be
// shown that all integers greater than 28123 can be written as the sum of two abundant numbers.)

class Problem23 extends Problem(23, "4179871"):
  def divisorSum(i: Int) =
    (2 to math.sqrt(i).toInt)
      .filter(i % _ == 0)
      .flatMap(f => if f * f == i then List(f)
                    else List(f, i / f))
      .sum + 1
  def answer(limit: Int) =
    // It's easy to do without the mutability here, but at the expense of performance
    // (at least, I couldn't find a version that didn't increase the runtime by 15x).
    val isSumOfTwoAbundants = Array.fill(limit)(false)
    val abundants = (2 until limit).filter(n => n < divisorSum(n))
    for a <- abundants
        b <- abundants.takeWhile(a + _ < limit)
    do isSumOfTwoAbundants(a + b) = true
    (1 until limit).filter(!isSumOfTwoAbundants(_)).sum
  def solve = answer(28124)
