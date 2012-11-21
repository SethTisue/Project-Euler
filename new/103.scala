package net.tisue.euler
import Euler._

// solves n = 6 in 4 seconds
class Problem103 extends Problem(103, "") {
  type Solution = List[List[Int]]
  def fact(n: Int) = (2 to n).product
  def choose(n: Int, k: Int) =
    fact(n) / (fact(k) * fact(n - k))
  def choices(n: Int) =
    (1 to n).map(choose(n, _)).toList
  def isSolution(s: Solution) = {
    def countsOk =
      s.map(_.size) == choices(s.head.size)
    def rangesOk =
      s.zip(s.tail).forall{case (s1, s2) => s1.last < s2.head}
    if(countsOk && rangesOk) {
      // if(s.head.size == 5) println((s.head.sum, s.head))
      true
    }
    else false
  }
  def augment(s: Solution, n: Int) =
    if(s.isEmpty) List(List(n))
    else
      (s.head :+ n) :: s.zip(s.tail).map{case (s1, s2) => (s2 ::: s1.map(n + _)).distinct} ::: List(List(n + s.head.sum))
  val recurse: (Int, Int, Int) => List[Solution] = memoize{(n, max, desiredSize) =>
    if(desiredSize < 0) Nil
    else if(n == 0)
      if(desiredSize == 0) List(Nil)
      else Nil
    else
      (1 to (n min max))
        .toList
        .flatMap(n1 => recurse(n - n1, (n1 min max) - 1, desiredSize - 1)
                         .map(augment(_, n1))
                         .filter(isSolution))
  }
  def candidates(n: Int, desiredSize: Int): List[Solution] =
    recurse(n, n, desiredSize)
  def solve =
    Stream.from(1).flatMap(candidates(_, 6)).head.head
}

/*
class Problem103 extends Problem(103, "") {
  def solutions(min: Int, size: Int, sumLimit: Int): List[List[Int]] =
    if(size == 0)
      if(sumLimit == 0) List(Nil)
      else Nil
    else if(min > sumLimit) Nil
    else for{n <- (min to sumLimit).toList
             ns <- solutions(n + 1, size - 1, sumLimit - n)
             if canAdd(n, ns)}
         yield n :: ns
  def canAdd(n: Int, ns: List[Int]) =
    hasEqualityProperty(n, ns) &&
    hasOrderingProperty(n, ns)
  def hasEqualityProperty(n: Int, ns: List[Int]) = {
    // assume ns is sorted ascending and has the property.
    n > ns.last
  }
  def hasOrderingProperty(n: Int, ns: List[Int]) = {
    // assume ns is sorted ascending and has the property.
    (1 until ns.size).forall(k => n + smallestSum(ns, k - 1)
  }
  def smallestSum(ns: List[Int], k: Int) = ns.take(k).sum
  def largestSum(ns: List[Int], k: Int) = ns.takeRight(k).sum
  def solve = solutions(min = 1, size = 4, sumLimit = 21)
}
n = 1: {1} = 1
n = 2: {1, 2} = 3
n = 3: {2, 3, 4} = 9
n = 4: {3, 5, 6, 7} = 21
n = 5: {6, 9, 11, 12, 13} = 51
n = 6: {11, 18, 19, 20, 22, 25} = 115
n = 7:

  1-sums 11-18-19-20-22-25
  2-sums 29-30-31-33-36-37-38-39-40-41-42-43-44-45-47
  3-sums 48-...-67

suppose we have 3-5-6.  how do we know if we can add 7?
suppose we know that 3-5-6 is special.
  we know that because the 1-sums are: 3-5-6
                   and the 2-sums are: 8-9-11
                   and the 3-sums are: 14
  7 is ok to add because:
    first, equality property:
      7 is not in the 1-sums or 2-sums or 3-sums
      7 plus any 1-sum is not in the 2-sums or 3-sums
      7 plus any 2-sum is not in the 3-sums
    second, size property:
      7 is greater than every 1-sum
      7 plus any 1-sum is greater than any 1-sum
      7 plus any 2-sum is greater than any 1-sum or 2-sum

suppose we have 6-9-11-12. how do we know if we can add 13?
suppose we know that 6-9-11-12 is special:
  we know that because the 1-sums are: 6-9-11-12                -> 6-9-11-12-13
                       the 2-sums are: 15-16-18-20-21-23        -> 15-16-18-20-21-23 - 19-22-24-25
                       the 3-sums are: 26-27-29-32              -> 26-27-29-32 - 28-29-31-33-34-36
                       the 4-sums are: 38
  13 is ok to add because:
    first, equality property:
      13 is not in any of the k-sums


class Problem103 extends Problem(103, "") {
  case class Subset(ns: List[Int], size: Int, sum: Int)
  type Solution = Set[Subset]
  def canAdd(n: Int, s1: List[Int], s2: List[Int]) = {
    val sum1 = s1.sum
    val sum2 = s2.sum
    (n + sum1 != sum2) &&
    (n + sum2 != sum1) &&
    (s1.size + 1 <= s2.size || sum1 + n > sum2) &&
    (s2.size + 1 <= s1.size || sum2 + n > sum1)
  }
  def canAddAll(s: Solution, c: Int) = {
    val pairs = for{ss1 <- s.toStream; ss2 <- s} yield (ss1, ss2)
    pairs.forall{case (ss1, ss2) => canAdd(c, ss1.ns, ss2.ns)}
  }
  def sol =
    for{a <- 1 to 10
        b <- (a + 1) to 10
        seed = Set(Subset(List(a, b), 2, a + b),
                   Subset(List(a), 1, a),
                   Subset(List(b), 1, b))
        c <- (b + 1) until (a + b)
        if canAddAll(seed, c)}
    yield seed ++ seed.map(x => Subset(c :: x.ns, x.size + 1, x.sum + c))
  def solve = sol
}

class Problem103 extends Problem(103, "") {
  val disjointSubsets: List[Int] => Stream[(List[Int], List[Int])] = memoize{ns =>
    if(ns.isEmpty) Stream((Nil, Nil))
    else
      for{(set1, set2) <- disjointSubsets(ns.tail)
          x <- List((set1, set2), (ns.head :: set1, set2), (set1, ns.head :: set2))}
      yield x
  }
  def canAdd(n: Int, s1: List[Int], s2: List[Int]) = {
    val sum1 = s1.sum
    val sum2 = s2.sum
    (n + sum1 != sum2) &&
    (n + sum2 != sum1) &&
    (s1.size + 1 <= s2.size || sum1 + n > sum2) &&
    (s2.size + 1 <= s1.size || sum2 + n > sum1)
  }
  def candidates(n: Int, desiredSize: Int): List[List[Int]] = {
    def recurse(n: Int, max: Int, desiredSize: Int): List[List[Int]] =
      if(desiredSize < 0) Nil
      else if(n == 0)
        if(desiredSize == 0) List(Nil)
        else Nil
      else for{n1 <- (1 to (n min max)).toList
               cand <- recurse(n - n1, (n1 min max) - 1, desiredSize - 1)
               if disjointSubsets(cand).forall{case (s1, s2) => canAdd(n1, s1, s2)}
             }
           yield n1 :: cand
    recurse(n, n, desiredSize)
  }
  def solve = Stream.from(1).flatMap(candidates(_, 6)).head
}

class Problem103 extends Problem(103, "") {
  def candidates(n: Int, desiredSize: Int): List[List[Int]] = {
    def recurse(n: Int, max: Int, desiredSize: Int): List[List[Int]] =
      if(desiredSize < 0) Nil
      else if(n == 0)
        if(desiredSize == 0) List(Nil)
        else Nil
      else (1 to (n min max))
             .toList
             .flatMap(n1 => recurse(n - n1, (n1 min max) - 1, desiredSize - 1)
                              .filter(isSpecial)
                              .map(n1 :: _))
    recurse(n, n, desiredSize)
  }
  val disjointSubsets: List[Int] => Stream[(List[Int], List[Int])] = memoize{ns =>
    if(ns.isEmpty) Stream((Nil, Nil))
    else
      for{(set1, set2) <- disjointSubsets(ns.tail)
          x <- List((set1, set2), (ns.head :: set1, set2), (set1, ns.head :: set2))}
      yield x
  }
  def disjoint(ns1: List[Int], ns2: List[Int]) =
    (ns1 intersect ns2).isEmpty
  val isSpecial: List[Int] => Boolean = memoize{ns =>
    ns.size < 3 || ns.head < ns.last + ns.init.last && {
      println(ns)
      val pairs = disjointSubsets(ns)
      pairs.forall{case (s1, s2) => s1.sum > s2.sum || (s1.size == s2.size && s1.sum != s2.sum)}
    }
  }
  def solve = Stream.from(1).flatMap(candidates(_, 6)).find(isSpecial).get
}

class Problem103 extends Problem(103, "") {
  def candidates(n: Int, desiredSize: Int): List[List[Int]] = {
    def recurse(n: Int, max: Int, desiredSize: Int): List[List[Int]] =
      if(desiredSize < 0) Nil
      else if(n == 0)
        if(desiredSize == 0) List(Nil)
        else Nil
      else (1 to (n min max))
             .toList
             .flatMap(n1 => recurse(n - n1, (n1 min max) - 1, desiredSize - 1)
                              .filter(isSpecial)
                              .map(n1 :: _))
    recurse(n, n, desiredSize)
  }
  def subsets(ns: List[Int]): Stream[List[Int]] =
    if(ns.isEmpty) Stream(Nil)
    else {
      val recurse = subsets(ns.tail)
      recurse #::: recurse.map(ns.head :: _)
    }
  def disjoint(ns1: List[Int], ns2: List[Int]) =
    (ns1 intersect ns2).isEmpty
  val isSpecial: List[Int] => Boolean = memoize{ns =>
    ns.size < 3 || ns.head < ns.last + ns.init.last && {
      println(ns)
      val ss = subsets(ns)
      val pairs = for(s1 <- ss; s2 <- ss; if s1.nonEmpty && s2.nonEmpty && s1.size >= s2.size && disjoint(s1, s2))
                  yield (s1, s2)
      pairs.forall{case (s1, s2) => s1.sum > s2.sum || (s1.size == s2.size && s1.sum != s2.sum)}
    }
  }
  def solve = Stream.from(1).flatMap(candidates(_, 6)).find(isSpecial).get
}
*/
