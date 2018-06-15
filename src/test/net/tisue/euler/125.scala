package net.tisue.euler

// The palindromic number 595 is interesting because it can be written as the sum of consecutive
// squares: 6^2 + 7^2 + 8^2 + 9^2 + 10^2 + 11^2 + 12^2.
//
// There are exactly eleven palindromes below one-thousand that can be written as consecutive square
// sums, and the sum of these palindromes is 4164. Note that 1 = 0^2 + 1^2 has not been included as
// this problem is concerned with the squares of positive integers.
//
// Find the sum of all the numbers less than 10^8 that are both palindromic and can be written as the
// sum of consecutive squares.

class Problem125 extends Problem(125, "2906969179") {
  def isPalindrome(k: Int) = k.toString == k.toString.reverse
  val limit = 100000000
  def solve =
    LazyList.from(1)
      .map(x => x * x)
      .takeWhile(_ < limit / 2)
      .tails
      .flatMap(_.scanLeft(0)(_ + _)
                .drop(2)  // ignore "sums" with zero or one term
                .takeWhile(_ < limit))
      .filter(isPalindrome)
      .toSeq
      .distinct
      .map(_.toLong)  // otherwise sum will overflow
      .sum
}
