package net.tisue.euler
import Primes._

// The first eight rows of Pascal's triangle contain twelve distinct numbers: 1, 2, 3, 4, 5, 6, 7,
// 10, 15, 20, 21 and 35.  A positive integer n is called squarefree if no square of a prime divides
// n. Of the twelve distinct numbers in the first eight rows of Pascal's triangle, all except 4 and
// 20 are squarefree. The sum of the distinct squarefree numbers in the first eight rows is 105.
// Find the sum of the distinct squarefree numbers in the first 51 rows of Pascal's triangle.

// To make this fast we represent a number as a Seq of prime factors.

class Problem203 extends Problem(203, "34029210557338"):
  def factorial(n: Int) =
    (2 to n).flatMap(factors)
  def combinations(n: Int, r: Int) =
    factorial(n) diff (factorial(r) ++ factorial(n - r))
  def row(n: Int) =
    (0 to n).map(combinations(n, _))
  def isSquareFree(ns: Seq[Int]) =
    ns.size == ns.distinct.size
  def distincts(rowCount: Int) =
    (0 until rowCount).flatMap(row).toSet.filter(isSquareFree)
  def answer(rowCount: Int) =
    distincts(rowCount).map(_.map(BigInt(_)).product).sum
  def solve = answer(51)

