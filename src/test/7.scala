package net.tisue.euler
import Primes._

// What is the 10001st prime number?

class Problem7 extends Problem(7, "104743") {
  def solve = primes(10000)
}
