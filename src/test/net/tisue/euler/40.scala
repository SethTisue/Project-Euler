package net.tisue.euler

// An irrational decimal fraction is created by concatenating the positive integers:
// 0.123456789101112131415161718192021...
// It can be seen that the 12th digit of the fractional part is 1.
// If dn represents the nth digit of the fractional part, find the value of the following expression.
// d1 x d10 x d100 x d1000 x d10000 x d100000 x d1000000

class Problem40 extends Problem(40, "210") {
  def solve = {
    val digits = LazyList.from(1).flatMap(_.toString).map(_.asDigit)
    List(1, 10, 100, 1000, 10000, 100000, 1000000).map(n => digits(n - 1)).product
  }
}
