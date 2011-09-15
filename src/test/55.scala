package net.tisue.euler

// How many Lychrel numbers are there below ten thousand?  (You may assume 50 steps is adequate to
// test.)

class Problem55 extends Problem(55, "249") {
  def solve = {
    def isPalindromic(s: String) = s == s.reverse
    def lychrelStream(n: BigInt): Stream[BigInt] = {
      val next = n + BigInt(n.toString.reverse)
      if(isPalindromic(next.toString)) Stream(next)
      else next #:: lychrelStream(next)
    }
    (1 to 10000).count(lychrelStream(_).take(50).size == 50)
  }
}
