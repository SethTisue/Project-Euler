package net.tisue.euler

// The ordered set 8128, 2882, 8281, has three interesting properties.  The set is cyclic, in that
// the last two digits of each number is the first two digits of the next number (including the last
// number with the first).  Each polygonal type: triangle (P(3,127)=8128), square (P(4,91)=8281),
// and pentagonal (P(5,44)=2882), is represented by a different number in the set.  This is the only
// set of 4-digit numbers with this property.  Find the sum of the only ordered set of six cyclic
// 4-digit numbers for which each polygonal type (triangular through octagonal), is represented by a
// different number in the set.

class Problem61 extends Problem(61, "28684") {
  def solve = {
    def concat(n1:Int,n2:Int) = n1 * 100 + n2
    def p(s:Int,n:Int) = ((s - 2) * n * n - (s - 4) * n) / 2  // en.wikipedia.org/wiki/Polygonal_number
    def inRange(n:Int) = n >= 1000 && n < 10000
    def ps(s:Int) = Stream.from(1).map(p(s,_)).dropWhile(!inRange(_)).takeWhile(inRange).toSet
    val polygonals = (3 to 8).flatMap(ps).toSet
    val pMap = (3 to 8).map(n => (n, ps(n))).toMap
    def isPolygonal(n:Int) = polygonals(n)
    def polygonalTypes(n:Int) = (3 to 8).filter(pMap(_)(n))
    def isSolution(ns:List[Int]) = {
      def recurse(ns:List[Int],types:Set[Int]):Boolean =
        ns.isEmpty || polygonalTypes(ns.head).exists(s => types(s) && recurse(ns.tail,types - s))
      recurse(ns,(3 to 8).toSet)
    }
    // This would be more general if the nested loops were expressed using recursion
    // rather than written out like this, but it wouldn't help the readability any...
    val cycles =
      for{n1 <- 0 to 99;
          n2 <- 0 to 99; if isPolygonal(concat(n1,n2))
          n3 <- 0 to 99; if isPolygonal(concat(n2,n3))
          n4 <- 0 to 99; if isPolygonal(concat(n3,n4))
          n5 <- 0 to 99; if isPolygonal(concat(n4,n5))
          n6 <- 0 to 99; if isPolygonal(concat(n5,n6)) && isPolygonal(concat(n6,n1))
          cycle = List(concat(n1,n2),concat(n2,n3),concat(n3,n4),concat(n4,n5),concat(n5,n6),concat(n6,n1))}
      yield cycle
    cycles.find(isSolution).get.sum
  }
}
