package net.tisue.euler

// Given that A is an optimum special sum set for n = 7, find its set string.

// This is a pretty lame solution.  And it only runs fast (6 seconds) because
// we cheat somewhat and start searching at 20.  If we start searching at 11,
// it takes well over a minute.

// Maybe I'll improve it someday.
  
class Problem103 extends Problem(103, "20313839404245") {
  val ceil = 300
  type SumSet = List[List[Int]]
  def augment(x: Int, ss: SumSet) =
    if(ss.isEmpty)
      List(List(x))
    else
      (ss.head :+ x) +: ss.sliding(2).collect{case List(l1, l2) => l2 ++ l1.map(_ + x)}.toList :+ List(ss.head.sum + x)
  def isSpecial(ss: SumSet): Boolean =
    ss.size < 2 || ss.sliding(2).forall{
      case List(xs1, xs2) =>
        xs1.size == xs1.distinct.size &&
        xs2.size == xs2.distinct.size &&
        xs1.last < xs2.head
      case _ => throw new IllegalStateException
    }
  def solve = {
    val solutions =
      for {
        n1 <- Iterator.from(20).takeWhile(_ <= ceil)
        _ = println(n1)
        ss1 = augment(n1, Nil)
        if isSpecial(ss1)
        n2 <- Iterator.from(n1 + 1).takeWhile(_ + n1 <= ceil)
        ss2 = augment(n2, ss1)
        if isSpecial(ss2)
        n3 <- Iterator.from(n2 + 1).takeWhile(_ + n2 + n1 <= ceil)
        ss3 = augment(n3, ss2)
        if isSpecial(ss3)
        n4 <- Iterator.from(n3 + 1).takeWhile(_ + n3 + n2 + n1 <= ceil)
        ss4 = augment(n4, ss3)
        if isSpecial(ss4)
        n5 <- Iterator.from(n4 + 1).takeWhile(_ + n4 + n3 + n2 + n1 <= ceil)
        ss5 = augment(n5, ss4)
        if isSpecial(ss5)
        n6 <- Iterator.from(n5 + 1).takeWhile(_ + n5 + n4 + n3 + n2 + n1 <= ceil)
        ss6 = augment(n6, ss5)
        if isSpecial(ss6)
        n7 <- Iterator.from(n6 + 1).takeWhile(_ + n6 + n5 + n4 + n3 + n2 + n1 <= ceil)
        ss7 = augment(n7, ss6)
        if isSpecial(ss7)
      } yield ss7.head
    solutions.next.mkString
  }
}
