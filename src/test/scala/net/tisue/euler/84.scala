package net.tisue.euler

// In a game of Monopoly, statistically it can be shown that the three most
// popular squares, in descending order, are JAIL, E3, and GO (squares 10, 24, 0
// = 102400). If, instead of using two 6-sided dice, two 4-sided dice are used,
// find the three winners.
//
// This can be solved by Monte Carlo simulation, but I chose to approach it
// analytically as a Markov chain problem:
//   http://www.math.yorku.ca/Who/Faculty/Steprans/Courses/2042/Monopoly/Stewart2.html
//   http://www.tkcs-collins.com/truman/monopoly/monopoly.shtml
//   http://en.wikipedia.org/wiki/Markov_matrix
//
// The rule about rolling doubles three times doesn't affect the probabilities
// enough to change the final answer, so we just ignore that rule.
//
// Someone in the forum points out that "If you are on CH3 and go back on CC3,
// shouldn't you take another card?" but that doesn't affect the final answer
// either.

object Problem84 extends Problem(84, solution = "101524") {

  val die = 4
  val names = List("GO", "A1", "CC1", "A2", "T1", "R1", "B1", "CH1", "B2", "B3",
                   "JAIL", "C1", "U1", "C2", "C3", "R2", "D1", "CC2", "D2", "D3",
                   "FP", "E1", "CH2", "E2", "E3", "R3", "F1", "F2", "U2", "F3",
                   "G2J", "G1", "G2", "CC3", "G3", "R4", "CH3", "H1", "T2", "H2")
  val squares: Map[String, Int] = // from name to number
    names.zipWithIndex.toMap
  val namesCycle: LazyList[String] =
    names.to(LazyList).circular

  def limit(squareNumber: Int): Int =
    (squareNumber + names.size) % names.size

  def nextSquare(square: Int, roll: Int): List[BigRational] = {
    // kind is R for railroad or U for utility
    def nextSpecial(square: Int, kind: Char): Int =
      limit(square +
        namesCycle
          .drop(square)
          .indexWhere(_.head == kind))
    val newSquare: Int =
      (square + roll) % names.size
    val nexts: List[Int] =
      names(newSquare) match {
        case "G2J" =>
          List(squares("JAIL"))
        case "CC1" | "CC2" | "CC3" =>
          List(squares("GO"), squares("JAIL")) ::: List.fill(14)(newSquare)
        case "CH1" | "CH2" | "CH3" =>
          List(squares("GO"), squares("JAIL"), squares("C1"),
               squares("E3"), squares("H2"), squares("R1"),
               nextSpecial(square, 'R'), nextSpecial(square, 'R'),
               nextSpecial(square, 'U'),
            limit(square - 3)) :::
          List.fill(6)(newSquare)
        case _ =>
          List(newSquare)
      }
    names.indices.toList.map(next =>
      new BigRational(
        nexts.count(_ == next),
        nexts.size * die * die))
  }

  // P is the Markov matrix
  val P: List[List[Double]] = {
    val zeroVector = List.fill(names.size)(0: BigRational)
    val rolls =
      for {
        die1 <- 1 to die
        die2 <- 1 to die
      }
      yield die1 + die2
    def row(i: Int): List[BigRational] =
      rolls.toList.foldLeft(zeroVector){(vec, roll) =>
        vec.lazyZip(nextSquare(i, roll)).map(_ + _)}
    names.indices.toList.map(row(_).map(_.toDouble))
  }

  def matrixMul(m1: List[List[Double]], m2: List[List[Double]]) = {
    val indices = m1.indices.toList
    indices.map(i =>
      indices.map(j =>
        indices.map(r => m1(i)(r) * m2(r)(j))
          .sum))
  }

  // as k approaches infinity, all rows of P^k approach the stationary probability vector.
  // trial and error says k=150 gives accuracy to at least 3 decimal places
  def solve = {
    val stationaryProbabilityVector =
      Iterator.iterate(P)(matrixMul(_, P))
        .drop(150).next.head
    import Ordering.Double.TotalOrdering
    stationaryProbabilityVector
      .zipWithIndex
      .sortBy(-_._1)
      .take(3)
      .map(_._2)
      .map("%02d".format(_))
      .mkString
  }

}
