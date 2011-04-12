package net.tisue.euler
import Euler._

// What is the millionth lexicographic permutation of the digits 0 through 9?

class Problem24 extends Problem(24, "2783915460") {
  def solve = (0 to 9).permutations.drop(999999).next.mkString
}
