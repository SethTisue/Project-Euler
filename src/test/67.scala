package net.tisue.euler

// Find the maximum sum travelling from the top of the triangle to the base.
// (same as problem 18, but with a much larger input)

// Note: The uses of zipped could also be expressed using zipWith, and the
// overall recursion is a fold (a foldRight if we don't reverse the input
// triangle).

class Problem67 extends Problem(67, "7273") {
  val triangle =
    io.Source.fromFile("dat/67.txt")
      .mkString.trim
      .split("\n").toList
      .map(_.split(" ").toList.map(_.toInt))
  def recurse(t: List[List[Int]]): Int =
    t match {
      case Seq(Seq(n)) => n
      // yuck. this is really cryptic - ST 11/13/09
      case r0::r1::rest =>
        val rr0 = (r0, r0.tail).zipped.map(_ max _)
        val rr1 = (r1, rr0).zipped.map(_ + _)
        recurse(rr1 :: rest)
    }
  def solve =
    recurse(triangle.reverse)
}
