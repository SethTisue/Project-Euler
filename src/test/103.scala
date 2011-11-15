package net.tisue.euler

// Given that A is an optimum special sum set for n = 7, find its set string.

class Problem103 extends Problem(103, "111819202225") {
  val ceil = 115
  type SumSet = List[List[Int]]
  def augment(x: Int, ss: SumSet) =
    if(ss.isEmpty)
      List(List(x))
    else
      (ss.head :+ x) +: ss.sliding(2).collect{case List(l1, l2) => l2 ++ l1.map(_ + x)}.toList :+ List(ss.head.sum + x)
  def isSpecial(ss: SumSet): Boolean =
    ss.forall(xs => xs.size == xs.toSet.size) &&
    ss.sliding(2).forall{case List(xs1, xs2) => xs1.size == xs1.distinct.size && xs2.size == xs2.distinct.size && xs1.last < xs2.head; case _ => true}
  def solve = {
    val solutions =
      for {
        n1 <- Stream.from(1)
        ss1 = augment(n1, Nil)
        if isSpecial(ss1)
        n2 <- Stream.from(n1 + 1).takeWhile(_ + ss1.head.sum <= ceil)
        ss2 = augment(n2, ss1)
        if isSpecial(ss2)
        n3 <- Stream.from(n2 + 1).takeWhile(_ + ss2.head.sum <= ceil)
        ss3 = augment(n3, ss2)
        if isSpecial(ss3)
        n4 <- Stream.from(n3 + 1).takeWhile(_ + ss3.head.sum <= ceil)
        ss4 = augment(n4, ss3)
        if isSpecial(ss4)
        n5 <- Stream.from(n4 + 1).takeWhile(_ + ss4.head.sum <= ceil)
        ss5 = augment(n5, ss4)
        if isSpecial(ss5)
        n6 <- Stream.from(n5 + 1).takeWhile(_ + ss5.head.sum <= ceil)
        ss6 = augment(n6, ss5)
        if isSpecial(ss6)
        // n7 <- Stream.from(n6 + 1).takeWhile(_ + ss6.head.sum <= ceil)
        // ss7 = augment(n7, ss6)
        // if isSpecial(ss7)
      } yield ss6.head
    solutions.head.mkString
  }
}
    
/*      


  def distinctPartitions(n: Int) = {
    def helper(n: Int, min: Int): List[List[Int]] = {
      if (n == 0)
        List(Nil)
      else
        (min to n).toList.flatMap(n2 => helper(n - n2, n2 + 1).map(n2 :: _))
    }
    helper(n, 1).sortBy(_.size)
  }
  val partitions =
    Stream.from(1).flatMap(distinctPartitions)
  def expand(xs: List[Int]): SumSet =
    xs.foldLeft(Nil: SumSet)(augment)
  def isSpecial(ss: SumSet): Boolean =
    ss.forall(xs => xs.size == xs.toSet.size) && (ss.size < 2 || ss.head.last < ss.tail.head.head && isSpecial(ss.tail))
  def optimalSpecialSumSet(n: Int) =
    partitions.filter(_.size == n).map(expand).find(isSpecial).map(_.head).get
  def solve = optimalSpecialSumSet(5).mkString
}

( (3) )
( (3, 5) (8) )

now add a 6 to this

(3 5 6) (8 9 11)




( (1) )
( (1, 2) (3) )
( (2, 3, 4) (5, 6, 7) (9) )

( (3, 5, 6, 7) (8, 9, 10, 11, 12, 13) (


  
( (3, 5, 6) (8, 9, 11) (14) )


  def subsets(xs: Set[Int]) =
    xs.toSeq.permutations.toList.flatMap(subset => (1 until xs.size).map(subset.toSeq.take)).map(_.toSet).distinct.toList
  def isSpecial(xs: Set[Int]) = {
    val subs = subsets(xs)
    val pairs =
      for {
        s1 <- subs
        s2 <- subs
        if s1 != s2 && s1.size >= s2.size
      } yield (s1, s2)
    pairs.forall{case (s1, s2) =>
      val result = if (s1.size == s2.size) s1.sum != s2.sum else s1.sum > s2.sum
      result
    }
  }

  def solve = List(Set(1),
                   Set(1, 2),
                   Set(2, 3, 4),
                   Set(3, 5, 6, 7),
                   Set(6, 9, 11, 12, 13),
                   Set(11, 18, 19, 20, 22, 25),
                   Set(11, 17, 20, 22, 23, 24)).forall(isSpecial)
}

1       {1}
10      {2}
100     {3}
1000    {4}
10000   {5}

11      {2, 1}   3
101     {3, 1}   4
110     {3, 2}   5
1001    {4, 1}   5
1010    {4, 2}   6
1100    {4, 3}   7
10001   {5, 1}   6
10010   {5, 2}   7

111     {3, 2, 1}  6
1011    {4, 2, 1}  7
1101    {4, 3, 1}  8
1110    {4, 3, 2}  9

1111    {4, 3, 2, 1} 10

10011   {5, 2, 1}
10100   {5, 3}
10101   {5, 3, 1}
10110   {5, 3, 2}
10111   {5, 3, 2, 1}
11000   {5, 4}
11001   {5, 4, 1}
11010   {5, 4, 2}
11011   {5, 4, 2, 1}
11100   {5, 4, 3}
11101   {5, 4, 3, 1}
11110   {5, 4, 3, 2}

11111   {5, 4, 3, 2, 1}

*/
