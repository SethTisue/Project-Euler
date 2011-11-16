package net.tisue.euler

// m(k) is the minimum number of multiplications to compute n^k.  e.g. m(15) = 5.
// (Because 1 + 1 = 2, 2 + 1 = 3, 3 + 3 = 6, 6 + 6 = 12, 12 + 3 = 15.)
// For 1 <= k <= 200, find the sum of m(k).

// solution plan:
//   0 steps: ((1))
//   1 step : ((1 2))
//   2 steps: ((1 2 3) (1 2 4))
//   3 steps: ((1 2 3 4) (1 2 3 5) (1 2 4 6) (1 2 4 7) (1 2 4 8))
//   ...
// generate each set from the last, noting new highest n's in a table.  stop when the
// table is full to 200.  prune any search branch with n > 200.

// After having got the right answer with some much-too-slow brute force code, I started googling.
// The sequence is oeis.org/A003313 and according to en.wikipedia.org/wiki/Addition_chain
// "Calculating an addition chain of minimal length is not easy" -- no algorithm exists that is
// simple, general, and fast.

// However from looking at the forums I learned that for small k (and 200 is small), you only need
// to consider "star chains", where you're always including the current largest number in the next
// sum.  With that knowledge the code is simple and fast.

import collection.immutable.BitSet

class Problem122Imperative extends Problem(122, "1582") {
  def children(s: BitSet): List[BitSet] =
    s.toList
      .map(k => s + (k + s.last))
      .takeWhile(_.last <= 200)
  def solve = {
    var cur = List(BitSet(1))
    var m = Map[Int, Int]()
    def isMinimal(s: BitSet) =
      !m.contains(s.last)
    while(cur.nonEmpty) {
      cur = cur.flatMap(children).filter(isMinimal)
      m ++= cur.map(bs => bs.last -> bs.size)
    }
    m.values.map(_ - 1).sum
  }
}

// since we have two state variables it's tricky to restate this without using "var" or "mutable".
// here's my attempt, but all the tupling and untupling makes kind of ugly.  I'd love to know
// if there's a better way.

class Problem122Functional extends Problem(122, "1582") {
  def children(s: BitSet): List[BitSet] =
    s.toList
      .map(k => s + (k + s.last))
      .takeWhile(_.last <= 200)
  def iterate(cur: List[BitSet], m: Map[Int, Int]) = {
    def isMinimal(s: BitSet) = !m.contains(s.last)
    val next = cur.flatMap(children).filter(isMinimal)
    (next, m ++ next.map(bs => bs.last -> bs.size))
  }
  def solve = {
    val start = (List(BitSet(1)), Map[Int, Int]())
    Iterator.iterate(start)(Function.tupled(iterate))
      .find(_._1.isEmpty).get
      ._2.values.map(_ - 1).sum
  }
}
