package net.tisue.euler
import Euler._

// The input file contains fifty different Sudoku puzzles ranging in difficulty, but all with unique
// solutions.  By solving all fifty puzzles find the sum of the 3-digit numbers found in the top
// left corner of each solution grid (483 for the first puzzle).

// Each puzzle is a list of 81 lists of integers 1-9, each list representing possibilities which
// have not yet been ruled out.  The puzzle is solved when all the possibility lists have length 1.
// Each group (row, column, or 3x3 box) is a list of 9 indices into the puzzle.

// I did not read the Python code at http://www.eddaardvark.co.uk/sudokusolver.html but I more or
// less follow the suggested algorithm.  Note that in the web page's terminology, puzzle #1 is
// "trivial" and puzzles #2 through #5 are "easy", but some subsequent puzzles are neither.  solve1
// corresponds to the "Applying the given numbers" section, solve2 corresponds to the "Looking for
// Singletons" section, I skipped the "Pairs" part, and solve3 corresponds to the final phase,
// "Eliminating Bad Guesses", where we just use trial and error.

class Problem96 extends Problem(96, "24702") {
  type Puzzle = List[List[Int]]  // 81 lists of integers in 1-9 range
  type Group = List[Int]         // 9 integers in 0-80 range
  val groups: List[Group] = {
    val rows    = for(i <- (0 to 8).toList) yield (0 to 80).toList.filter(_ / 9 == i)
    val columns = for(i <- (0 to 8).toList) yield (0 to 80).toList.filter(_ % 9 == i)
    val boxes   = for(i <- (0 to 2).toList; j <- 0 to 2)
                  yield (0 to 80).toList.filter(n => n / 27 == i && n % 9 / 3 == j)
    rows ::: columns ::: boxes
  }
  // from initial guess x0, iterate until we find x such that fn(x) = x
  def fixedPoint[A](x0: A)(fn: (A) => A): A = {
    val x = fn(x0)
    if(x == x0) x
    else fixedPoint(x)(fn)
  }
  // I think that solve1, solve2, and solve3 could probably all be clarified, and their
  // interrelation could probably be clarified as well, but I feel like I've already spent enough
  // time on this.  The basic idea is that after solve2 makes a change, solve1 must be run to
  // exhaustion, and after solve3 makes a guess, solve2 and solve1 must be run to exhaustion.
  def solve1(puzzle: Puzzle) =
    groups.foldLeft(puzzle){(puzzle, group) =>
      val usedDigits = group.map(puzzle(_)).filter(_.size == 1).map(_.head)
      puzzle.zipWithIndex.map{case (entry, index) =>
        if(!group.contains(index) || entry.size == 1)
          entry
        else
          entry.filter(!usedDigits.contains(_))}}
  def solve2(puzzle: Puzzle) =
    groups.foldLeft(puzzle){(puzzle, group) =>
      val v =
        (1 to 9)
          .filter(d => group.filter(puzzle(_).size > 1)
                         .flatMap(puzzle(_))
                         .count(_ == d) == 1)
      v.foldLeft(puzzle){(puzzle, d) =>
        val start =
          puzzle.zipWithIndex.map{case (entry, index) =>
            if(group.contains(index) && entry.contains(d))
              List(d)
            else entry}
        fixedPoint(start)(solve1)}}
  def solve1and2(puzzle: Puzzle) =
    fixedPoint(fixedPoint(puzzle)(solve1))(solve2)
  def isValid(puzzle: Puzzle) =
    groups.forall(g => g.flatMap(puzzle(_)).toSet.size == 9)
  def solve3(puzzle: Puzzle): Option[Puzzle] =
    if(!isValid(puzzle))
      None
    else
      puzzle.indexWhere(_.size > 1) match {
        case -1 =>
          Some(puzzle)
        case index =>
          puzzle(index)
            .flatMap{guess =>
              solve3(fixedPoint(puzzle.updated(index, List(guess)))(solve1and2))}
            .headOption
      }
  val puzzles: Iterator[Puzzle] =
    io.Source.fromFile("dat/96.txt")
      .getLines.filterNot(_.startsWith("Grid"))
      .grouped(9)
      .map(_.mkString.view.filter(_.isDigit).map(_.asDigit).toList
            .map(d => if(d == 0) (1 to 9).toList
                      else List(d)))
  def solve =
    puzzles.flatMap(p => solve3(solve1and2(p)))
     .map(_.take(3).map(_.head).mkString.toInt)
     .sum
}
