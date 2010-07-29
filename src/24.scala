package net.tisue.euler
import Euler._

// What is the millionth lexicographic permutation of the digits 0 through 9?

// This takes 30 seconds.  (Using Stream[List[A]] instead doesn't help.)  Drastically faster
// solutions are possible by being clever about not actually generating all of the permutations.

class Problem24 extends Problem(24, "2783915460") {
  def solve = permute((0 to 9).toList)(999999).mkString
}
