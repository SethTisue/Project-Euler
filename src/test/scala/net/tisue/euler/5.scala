package net.tisue.euler

// What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?

object Problem5 extends Problem(5, "232792560") {
  // en.wikipedia.org/wiki/Least_common_multiple#Calculating_the_least_common_multiple
  def lcm(a: BigInt, b: BigInt): BigInt =
    a * b / (a gcd b)
  def solve = (BigInt(2) to 20).reduce(lcm)
}
