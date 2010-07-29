package net.tisue.euler
import Euler._

// Find the sum of all numbers, less than one million, which are
// palindromic in base 10 and base 2 (example: 585 = 1001001001).

class Problem36 extends Problem(36, "872187") {
  def solve = {
    def isPalindromic(s: String) = s == s.reverse
    def qualifies(n: Int) = isPalindromic(n.toString) && isPalindromic(n.toBinaryString)
    (1 until 1000000 by 2).filter(qualifies).sum
  }
}
