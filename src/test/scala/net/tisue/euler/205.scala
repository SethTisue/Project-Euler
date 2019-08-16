package net.tisue.euler

// Peter has nine four-sided (pyramidal) dice, each with faces numbered 1, 2, 3, 4.
// Colin has six six-sided (cubic) dice, each with faces numbered 1, 2, 3, 4, 5, 6.
// Peter and Colin roll their dice and compare totals: the highest total wins. The result is a draw if
// the totals are equal.
// What is the probability that Pyramidal Pete beats Cubic Colin? Give your answer rounded to seven
// decimal places in the form 0.abcdefg

object Problem205 extends Problem(205, "0.5731441") {
  def rolls(n: Int, sides: Int): List[Int] =
    if(n == 0)
      List(0)
    else
      (1 to sides).toList
        .flatMap(roll => rolls(n - 1, sides)
                           .map(_ + roll))
  def counts[T](ns: List[T]): Map[T, Int] = {
    def increment(map: Map[T, Int], key: T) =
      map.updated(key, map(key) + 1)
    ns.foldLeft(Map[T, Int]().withDefaultValue(0))(increment)
      .to(Map)
  }
  def answer(n1: Int, sides1: Int, n2: Int, sides2: Int) = {
    val firstWins =
      for {
        (sum1, count1) <- counts(rolls(n1, sides1))
        (sum2, count2) <- counts(rolls(n2, sides2))
        if sum1 > sum2
      } yield count1 * count2
    firstWins.map(_.toLong).sum /
      (math.pow(sides1, n1) * math.pow(sides2, n2))
  }
  def solve = "%.7f".format(answer(9, 4, 6, 6))
}
