package net.tisue.euler
import Euler._

// THIS IS BROKEN.  I got the right answer for the wrong reason.
// When I changed it to use Scala 2.9's combinations method, it broke
// because old method for that gives the answers in a different order.
// I looked on the forum and it's common for people to get my new wrong
// answer of 1256 and the reason is disallowing fractional intermediate
// results.  So this code needs to be redone to use rationals instead
// of integers.

// By using each of the digits from the set, {1, 2, 3, 4}, exactly once, and making use of the four
// arithmetic operations (+, -, *, /) and brackets/parentheses, it is possible to form different
// positive integer targets.
// For example,
//  8 = (4 * (1 + 3)) / 2
//  14 = 4 * (3 + 1 / 2)
//  19 = 4 * (2 + 3) - 1
//  36 = 3 * 4 * (2 + 1)
// Note that concatenations of the digits, like 12 + 34, are not allowed.
// Using the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different target numbers of
// which 36 is the maximum, and each of the numbers 1 to 28 can be obtained before encountering the
// first non-expressible number.
// Find the set of four distinct digits, a < b < c < d, for which the longest set of consecutive
// positive integers, 1 to n, can be obtained, giving your answer as a string: abcd.

class Problem93 extends Problem(93, "1258") {
  def solve = {
    sealed abstract class Item
    case class Operator(c: Char, fn: (Int, Int) => Option[Int]) extends Item
    case class Digit(n: Int) extends Item
    val operators = List(Operator('+', (a, b) => Some(a + b)),
                         Operator('-', (a, b) => Some(a - b)),
                         Operator('*', (a, b) => Some(a * b)),
                         Operator('/', (a, b) => if(b != 0 && a % b == 0) Some(a / b)
                                                 else None))
    def expressions(digits: List[Digit], stackHeight: Int): List[List[Item]] = {
      def pushes =
        for(d <- digits; e <- expressions(digits diff List(d), stackHeight + 1))
        yield d :: e
      def pops =
        for(o <- operators; e <- expressions(digits, stackHeight - 1))
        yield o :: e
      if(stackHeight < 0) Nil
      else if(stackHeight == 1 && digits.isEmpty) List(Nil)
      else if(stackHeight < 2) pushes
      else (pushes ::: pops)
    }
    def eval(items: List[Item], stack: List[Int]): Option[Int] =
      if(items.isEmpty)
        Some(stack.head)
      else items.head match {
        case Operator(_, fn) =>
          fn(stack(0), stack(1)) match {
            case Some(n) =>
              eval(items.tail, n :: stack.drop(2))
            case None => None
          }
        case Digit(n) =>
          eval(items.tail, n :: stack)
      }
    def targets(ns: List[Int]): Set[Int] =
      expressions(ns.map(Digit(_)), 0)
        .flatMap(e => eval(e, Nil))
        .toSet
        .filter(_ > 0)
    def smallestMissing(ns: Set[Int]): Int =
      Stream.from(1).find(!ns.contains(_)).get
    (1 to 9).toList.combinations(4).toList.maxBy(ns => smallestMissing(targets(ns)))
      .mkString
  }
}
