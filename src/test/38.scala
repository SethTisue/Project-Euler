package net.tisue.euler
import Euler._
import annotation.tailrec

// What is the largest 1 to 9 pandigital 9-digit number that can be
// formed as the concatenated product of an integer with (1,2, ... , n)
// where n > 1?
// (Example #1: 192x(1 to 3) = 192 384 576)
// (Example #2: 9x(1 to 9) = 9 18 27 36 45)

class Problem38 extends Problem(38, "932718654") {
  def solve = {
    def findPandigital(input: Int): Option[Int] = {
      def isPandigital(s: String) = s.sorted == "123456789"
      @tailrec def recurse(n: Int,result: String): Option[Int] =
        if(result.size > 9) None
        else if(isPandigital(result)) Some(result.toInt)
        else recurse(n + 1, result + n * input)
      recurse(1, "")
    }
    (1 to 9999).flatMap(findPandigital).max
  }
}
