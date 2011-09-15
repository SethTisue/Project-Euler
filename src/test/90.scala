package net.tisue.euler

// [...]

class Problem90 extends Problem(90, "1217") {
  def solve = {
     val squares = List("01", "04", "09", "16", "25", "36", "49", "64", "81")
    def isSolution(die1: Seq[Int], die2: Seq[Int]) = {
      val rolls = 
        for(d1 <- die1; d2 <- die2)
        yield d1.toString + d2.toString
      val allRolls = rolls.flatMap(x => List(x,
                                             x.replaceAll("6", "9"),
                                             x.replaceAll("9", "6")))
      squares.forall(s => allRolls.contains(s) ||
                          allRolls.contains(s.reverse.mkString))
    }
    val dies = (0 to 9).combinations(6).toSeq
    val pairs = for(die1 <- dies; die2 <- dies)
                yield (die1, die2)
    pairs.count((isSolution _).tupled) / 2
  }
}
