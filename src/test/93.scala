package net.tisue.euler

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

// You have to be careful here to allow fractional intermediate results on the way
// to getting an integer in the end, e.g. 1 / 2 + 3 / 6 = 1.

class Problem93 extends Problem(93, "1258") {
  type Number = BigRational
  val Zero: BigRational = 0
  sealed abstract class Item
  case class Operator(c: Char, fn: (Number, Number) => Option[Number]) extends Item
  case class Digit(n: Int) extends Item
  val operators = List(Operator('+', (a, b) => Some(a + b)),
                       Operator('-', (a, b) => Some(a - b)),
                       Operator('*', (a, b) => Some(a * b)),
                       Operator('/', (a, b) => if(b != Zero) Some(a / b)
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
  def eval(items: List[Item], stack: List[Number]): Option[Number] =
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
      .filter(_.denom == 1)
      .filter(_.toDouble > 0)
      .map(_.toDouble.toInt)
      .toSet
  def smallestMissing(ns: Set[Int]): Int =
    Stream.from(1).find(!ns.contains(_)).get
  def solve =
    (1 to 9).toList.combinations(4).toList.maxBy(ns => smallestMissing(targets(ns)))
      .mkString
}
