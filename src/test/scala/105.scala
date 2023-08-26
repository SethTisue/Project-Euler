package net.tisue.euler

// Identify the special sum sets in sets.txt and find the sum of their sums.

class Problem105 extends Problem(105, "73702"):
  type SumSet = List[List[Int]]
  def augment(ss: SumSet, x: Int) =
    if ss.isEmpty then
      List(List(x))
    else
      val middle =
        ss.sliding(2)
          .collect:
            case List(l1, l2) => l2 ++ l1.map(_ + x)
          .toList
      (ss.head :+ x) +: middle :+ List(ss.head.sum + x)
  def isSpecial(ss: SumSet): Boolean =
    ss.size < 2 || ss.sliding(2).forall:
      case List(xs1, xs2) =>
        xs1.size == xs1.distinct.size &&
          xs2.size == xs2.distinct.size &&
          xs1.last < xs2.head
      case _ => throw IllegalStateException()
  def solve =
    io.Source.fromResource("105.txt").getLines
      .map(_.split(",").map(_.toInt).sorted)
      .map(_.foldLeft(Nil: SumSet)(augment))
      .filter(isSpecial)
      .map(_.head.sum)
      .sum
