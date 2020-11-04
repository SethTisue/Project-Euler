package net.tisue.euler

// By using each of the digits from the set {1, 2, 3, 4} exactly once, and
// making use of the four arithmetic operations (+, -, *, /) and parentheses,
// it is possible to form different positive integer targets.
//
// For example,
//  8 = (4 * (1 + 3)) / 2
//  14 = 4 * (3 + 1 / 2)
//  19 = 4 * (2 + 3) - 1
//  36 = 3 * 4 * (2 + 1)
//
// Note that concatenations of the digits, like 12 + 34, are not allowed.  Using
// the set, {1, 2, 3, 4}, it is possible to obtain thirty-one different target
// numbers of which 36 is the maximum, and each of the numbers 1 to 28 can be
// obtained before encountering the first non-expressible number.  Find the set
// of four distinct digits, a < b < c < d, for which the longest set of
// consecutive positive integers, 1 to n, can be obtained, giving your answer as
// a string: abcd.

// You have to be careful here to allow fractional intermediate results on the
// way to getting an integer in the end, e.g. 1 / 2 + 3 / 6 = 1.

class Problem93 extends Problem(93, "1258"):

  val Zero = BigRational(0)

  type OperatorFunction =
    (BigRational, BigRational) => Option[BigRational]

  enum Item:
    case Digit(n: Int)
    case Operator(c: Char, fn: OperatorFunction)
  import Item.{ Digit, Operator }

  val operators =
    List(
      Operator('+', (a, b) => Some(a + b)),
      Operator('-', (a, b) => Some(a - b)),
      Operator('*', (a, b) => Some(a * b)),
      Operator('/', (a, b) => if b != Zero then Some(a / b)
                              else None))

  def expressions(digits: List[Digit], stackHeight: Int): List[List[Item]] =
    def pushes =
      for d <- digits
          e <- expressions(digits diff List(d), stackHeight + 1)
      yield d :: e
    def pops =
      for o <- operators
          e <- expressions(digits, stackHeight - 1)
      yield o :: e
    if stackHeight < 0 then
      Nil
    else if stackHeight == 1 && digits.isEmpty then
      List(Nil)
    else if stackHeight < 2 then
      pushes
    else
      pushes ::: pops

  def eval(items: List[Item], stack: List[BigRational]): Option[BigRational] =
    if items.isEmpty then
      Some(stack.head)
    else items.head match
      case Operator(_, fn) =>
        fn(stack(1), stack(0)) match
          case Some(n) =>
            eval(items.tail, n :: stack.drop(2))
          case None => None
      case Digit(n) =>
        eval(items.tail, BigRational(n) :: stack)

  def targets(ns: List[Int]): Set[Int] =
    expressions(ns.map(new Digit(_)), 0)  // by 3.0.0-M1 it will be possible to type-ascribe rather than use `new`
      .flatMap(e => eval(e, Nil))
      .filter(_.denom == 1)
      .map(_.numer.toInt)
      .filter(_ > 0)
      .toSet

  def smallestMissing(ns: Set[Int]): Int =
    LazyList.from(1).find(!ns.contains(_)).get

  def solve =
    (1 to 9).toList
      .combinations(4).toList
      .maxBy(ns => smallestMissing(targets(ns)))
      .mkString
