package net.tisue.euler

// A spider, S, sits in one corner of a cuboid room, measuring 6 by 5 by 3, and
// a fly, F, sits in the opposite corner. By travelling on the surfaces of the
// room the shortest "straight line" distance from S to F is 10 and the path is
// shown on the diagram.  However, there are up to three "shortest" path
// candidates for any given cuboid and the shortest route is not always integer.
// By considering all cuboid rooms up to a maximum size of M by M by M, there
// are exactly 2060 cuboids for which the shortest distance is integer when
// M=100, and this is the least value of M for which the number of solutions
// first exceeds two thousand; the number of solutions is 1975 when M=99.  Find
// the least value of M such that the number of solutions first exceeds one
// million.

// Rather than do brute force search of cuboids, I decided to generate
// Pythagorean triples using
// http://en.wikipedia.org/wiki/Pythagorean_triple#Parent.2Fchild_relationships
// .  This approach is more interesting than brute force and may prove useful
// for later problems.

// Once we have the triples we fit cuboids inside them.  Actually, we compute
// how many cuboids will fit, since the problem doesn't require us to generate
// actual cuboids, only count them.

// The problem with generating triples is that they don't arrive in any
// simple-to-characterize order, so it's hard to know how many triples we need
// to generate to get all the solutions for a given M.  To force the triples
// arrive in a useful order, we use a priority queue where the next parent we
// allow to spawn is the one with the smallest longer leg.

class Problem86 extends Problem(86, "1818") {

  def solve = {
     case class Triple(a: Int, b: Int, c: Int) {
      def * (k : Int) = Triple(a * k, b * k, c * k)
      def canonical = if(b > a) this else swap
      def swap = Triple(b, a, c)
      def children =
        List(Triple(     a - 2 * b + 2 * c,
                    2 * a -     b + 2 * c,
                    2 * a - 2 * b + 3 * c),
             Triple(     a + 2 * b + 2 * c,
                    2 * a +     b + 2 * c,
                    2 * a + 2 * b + 3 * c),
             Triple(  -  a + 2 * b + 2 * c,
                    -2 * a +     b + 2 * c,
                    -2 * a + 2 * b + 3 * c))
      // I no longer remember the logic behind this formula.  I think the idea is something like,
      // b can't be too big or too small or the shortest path changes.
      def cuboidCount = (b min (a / 2)) - (1 max (a - b)) + 1
    }

    val primitiveTriples = {
      implicit val ordering = new Ordering[Triple] {
        def compare(t1: Triple, t2: Triple) = t2.b compare t1.b }
      val heap = new collection.mutable.PriorityQueue[Triple]
      heap += Triple(3, 4, 5)
      Stream.continually{
        val result = heap.dequeue()
        heap ++= result.children.map(_.canonical)
        result
      }
    }

    // this gives us all the triples (primitive or not) where either leg
    // equals m and the other leg is at most 2m (since to fit cuboids
    // in the triple we will break the other leg into two parts)
    def triples(m: Int) =
      for {
        t1 <- primitiveTriples.takeWhile(_.b <= m * 2)
        t2 <- Stream.from(1).map(t1 * _).takeWhile(_.b <= m * 2)
        if t2.a == m || t2.b == m
      }
      yield if(t2.b == m) t2
            else t2.swap

    def cuboidCount(k: Int): Int =
      triples(k).map(_.cuboidCount).sum

    def partialSums(ns: Stream[Int]): Stream[Int] =
      ns.scanLeft(0)(_ + _)

    val solutionCounts =
      partialSums(Stream.from(1).map(cuboidCount))

    solutionCounts.takeWhile(_ < 1000000).size
  }
}
