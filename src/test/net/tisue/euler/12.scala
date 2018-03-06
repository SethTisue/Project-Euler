package net.tisue.euler
import Primes._

// What is the first triangle number to have over five hundred divisors?

object Problem12 extends Problem(12, "76576500") {
  val triangles = LazyList.from(1).map(n => n * (n + 1) / 2)
  def solve =
    triangles.tail.find(factorCounts(_).map(_ + 1).product > 500).get
}
