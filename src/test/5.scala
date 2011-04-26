package net.tisue.euler
import Euler._

// What is the smallest number that is evenly divisible by all of the numbers from 1 to 20?

class Problem5 extends Problem(5, "232792560") {
  def solve = {
     // en.wikipedia.org/wiki/Least_common_multiple#Calculating_the_least_common_multiple
    def lcm(a: BigInt, b: BigInt): BigInt = a * b / (a gcd b)
    (BigInt(2) to 20).reduceLeft(lcm)
  }
}