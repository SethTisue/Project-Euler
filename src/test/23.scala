package net.tisue.euler

// A number whose proper divisors when summed exceed the number is called abundant.  Find the sum of
// all the positive integers which cannot be written as the sum of two abundant numbers.  (It can be
// shown that all integers greater than 28123 can be written as the sum of two abundant numbers.)

class Problem23 extends Problem(23, "4179871") {
  def divisorSum(i: Int) =
    (2 to math.sqrt(i).toInt)
      .filter(i % _ == 0)
      .flatMap(f => if(f * f == i) List(f)
                    else List(f, i / f))
      .sum + 1
  def answer(limit: Int) = {
    val isSumOfTwoAbundants = Array.fill(limit)(false)
    val abundants = (2 until limit).filter(n => n < divisorSum(n))
    for {
      a <- abundants
      b <- abundants.takeWhile(a + _ < limit)
    } isSumOfTwoAbundants(a + b) = true
    (1 until limit).filter(!isSumOfTwoAbundants(_)).sum
  }
  def solve = answer(28124)
}
