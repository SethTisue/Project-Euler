package net.tisue.euler

import Primes.*
import scala.util.chaining.*

class Problem204 extends Problem(204, "2944730"):
  val ps = primes.takeWhile(_ < 100)
  def count(n: Long, p: Int): Int =
    if n * p > 1000000000L then
      0
    else
      ps.dropWhile(_ < p)
        .map(count(n * p, _))
        .sum
        .pipe(_ + 1)
  def solve = count(1, 1)
