package net.tisue.euler
import Primes._

// The first eight rows of Pascal's triangle contain twelve distinct numbers: 1, 2, 3, 4, 5, 6, 7,
// 10, 15, 20, 21 and 35.  A positive integer n is called squarefree if no square of a prime divides
// n. Of the twelve distinct numbers in the first eight rows of Pascal's triangle, all except 4 and
// 20 are squarefree. The sum of the distinct squarefree numbers in the first eight rows is 105.
// Find the sum of the distinct squarefree numbers in the first 51 rows of Pascal's triangle.

// To solve this, instead of using BigInt we represent numbers as lists of prime factors.
// Once that crucial representational choice is made, we can be careless about performance
// and still get a runtime of about 12 seconds.

class Problem203 extends Problem(203, "34029210557338") {
  def solve = {
    def factorial(n: Int): List[Int] = if(n == 1) Nil else (2 to n).flatMap(factors).toList
    def combinations(n: Int, r: Int) = factorial(n) diff (factorial(r) ++ factorial(n - r))
    def row(n: Int) = (0 to n).map(combinations(n,_)).toList
    def isSquareFree(ns: List[Int]) = ns.size == ns.toSet.size
    def distincts(rowCount: Int) = (0 until rowCount).flatMap(row).toSet.filter(isSquareFree)
    def answer(rowCount: Int) = distincts(rowCount).map(_.map(BigInt(_)).product).sum
    answer(51)
  }
}
