package net.tisue.euler

// Some positive integers n have the property that the sum [ n + reverse(n) ] consists entirely of
// odd (decimal) digits. For instance, 36 + 63 = 99 and 409 + 904 = 1313. We will call such numbers
// reversible; so 36, 63, 409, and 904 are reversible. Leading zeroes are not allowed in either n or
// reverse(n).  There are 120 reversible numbers below one-thousand.  How many reversible numbers
// are there below one-billion (10^9)?

// There are no reversible 9 digit numbers, so we only need to check up to 100 million, which we
// can do in 10 seconds.

class Problem145 extends Problem(145, "608720"):
  def allOdd(n: Int): Boolean =
    n == 0 || n % 2 == 1 && allOdd(n / 10)
  def reverse(n: Int, result: Int): Int =
    if n == 0 then result
    else reverse(n / 10, 10 * result + n % 10)
  def reversible(n: Int) =
    n % 10 != 0 && allOdd(n + reverse(n, 0))
  def solve =
    (1 until 100000000).count(reversible)
