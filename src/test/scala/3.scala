package net.tisue.euler
import Primes.*

// What is the largest prime factor of the number 600851475143 ?

class Problem3 extends Problem(3, "6857"):
  def solve =
    def largestPrimeFactor(n: Long): Long =
      val f = primes.find(n % _ == 0).get
      if f == n then f
      else largestPrimeFactor(n / f)
    largestPrimeFactor(600851475143L)
