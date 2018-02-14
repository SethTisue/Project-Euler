package net.tisue.euler
import Euler._

// Same as problem 108, just larger input size.  zzz

// The number we're looking for is highly composite, in fact, it has *all* possible prime factors up
// to some limit, e.g.: 2 x 3 x 5 x 7, except some of the early factors might be repeated, like
// 2 x 2 x 2 x 3 x 3 x 5 x 7.  We represent this a list of exponents: List(3, 2, 1, 1).

// We only need to check prime factors up to 53, since A046079(2 x 3 x ... x 53) = 7174453 which is
// more than four million.

class Problem110 extends Problem(110, "") {
  val target = 4000000
  // thank you On-Line Encyclopedia of Integer Sequences!
  def A018892(counts: List[Int]) =
    1 + A046079((1 + counts.head) :: counts.tail)
  def A046079(counts: List[Int]) =
    ((2 * counts.head - 1) * counts.tail.map(_ * 2 + 1).product - 1) / 2
  def search(counts: List[Int]): List[Int] = {
    if(A018892(counts) > target) counts
    else {
      val candidates = List((counts.head + 1) :: counts.tail)
      minimize(candidates.map(search))(eval)
    }
  }
  def eval(counts: List[Int]) =
    (counts zip primes).map{case (f, p) => BigInt(p) pow f}.product
  def solve = {
    val start = Stream.continually(0).zip(primes).takeWhile(_._2 <= 53).map(_._1).toList
    eval(search(start))
  }
}
