package net.tisue.euler
import Euler._

class Problem122 extends Problem(122, "") {
  val limit = 200
  def log2(n: Int): Int = if(n == 1) 0 else 1 + log2((n + 1)/ 2)
  def next(ns: List[Int]) =
    for(n1 <- ns; n2 <- ns; if n1 <= n2 && n1 + n2 > ns.head && n1 + n2 <= limit)
    yield {val x = (n1 + n2) :: ns; /*println(x);*/ x}
  def solve = {
    val chains = Stream.iterate(List(List(1))){z => z.flatMap(next).distinct.filter(x => x.size <= 2 * log2(x.head))}
    val c = chains.takeWhile(_.head.length <= 10).flatten
    val found = c.map(_.head).distinct.sorted.force
    println((c.size,found))
    (1 to 200).find(!found.contains(_))
  }
}

/*
iterative lengthening?
iterative shortening?

consider
m(23) = 10111 = 23 = 1 2 4 5 9 18 23
                   = 1 2 4 5 9 14 23
                   = 
class Problem122 extends Problem(122, "") {
  val limit = 32
  def next(ns: List[Int]) =
    for(n1 <- ns; n2 <- ns; if n1 <= n2 && n1 + n2 > ns.head && n1 + n2 <= limit)
    yield (n1 + n2) :: ns
  def solve = {
    val chains = Stream.iterate(List(List(1)))(_.flatMap(next))
    chains.take(10).map(_.map(_.head).distinct).foreach(println)
    (1 to limit)
      .map{n => val r = chains
                          .dropWhile(!_.exists(_.head == n))
                          .head
                          .find(_.head == n)
                          .get
                println((n, r.size, r.reverse.mkString(" "))); r}
  }
}
*/

  // val seen = collection.mutable.Map(1 -> 0)
  // def solve = {
  //   while(!seen.contains(200)) {
  //     for((k1, v1) <- seen.toList; (k2, v2) <- seen.toList)
  //       seen(k1
  //   (1 to 10).map(seen)
  // }
  // def reachables(n: Int): Set[Int] =
  //   if(n == 0) Set(1)
  //   else {
  //     val r = reachables(n - 1)
  //     val r2 = for(r1 <- r; r2 <- r)
  //              yield r1 + r2
  //     println((n, r2))
  //     r ++ r2
  //   }
  // def m(k: Int) = Stream.from(0).find(n => reachables(n)(k)).get
  // def solve = reachables(3)


// m( 1) =     1 = 1

// m( 2) =    10 = 2 = 1 2
// m( 3) =    11 = 3 = 1 2 3

// m( 4) =   100 = 2 2 = 1 2 4
// m( 5) =   101 = 5 = 1 2 4 5
// m( 6) =   110 = 2 3 = 1 2 4 6
// m( 7) =   111 = 7 = 1 2 4 6 7

// m( 8) =  1000 = 2 2 2 = 1 2 4 8
// m( 9) =  1001 = 3 3 = 1 2 3 6 9
// m(10) =  1010 = 2 5 = 1 2 4 5 10
// m(11) =  1011 = 11 = 1 2 4 8 10 11
// m(12) =  1100 = 2 2 3 = 1 2 4 8 12   * 
// m(13) =  1101 = 13 = 1 2 4 8 12 13
// m(14) =  1110 = 2 7 = 1 2 4 6 7 14
// m(15) =  1111 = 3 5 = 1 2 4 5 10 15

// m(16) = 10000 = 2 2 2 2 = 1 2 4 8 16
// m(17) = 10001 = 17 = 1 2 4 8 16 17
// m(18) = 10010 = 2 3 3 = 1 2 3 6 9 18
// m(19) = 10011 = 19 = 1 2 4 8 16 18 19
// m(20) = 10100 = 2 2 5 = 1 2 4 5 10 20
// m(21) = 10101 = 3 7 = 1 2 3 4  7 14 21
// m(22) = 10110 = 2 11 = 1 2 4 8 16 20 22
// m(23) = 10111 = 23 = 1 2 4 5  9 18 23 = *
// m(24) = 11000 = 2 2 2 3 = 1 2 4 8 16 24
// m(25) = 11001 = 5 5 = 1 2 4 8 16 24 25
// m(26) = 11010 = 2 13 = 1 2 4 8 16 24 26
// m(27) = 11011 = 3 3 3 = 1 2 4 8  9 18 27
