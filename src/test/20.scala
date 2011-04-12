package net.tisue.euler
import Euler._

// Find the sum of the digits in the number 100!.

class Problem20 extends Problem(20, "648") {
  val factorials = BigInt(1) #:: Stream.from(1).scanLeft(BigInt(1))(_ * _)
  def solve = factorials(100).digits.sum
}
