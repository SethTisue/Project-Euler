package net.tisue.euler

import annotation.tailrec

// Given that L is the length of the wire, for how many values of L <= 2,000,000 can exactly one
// right angle triangle be formed?

// This is clumsy and runs slow (30 seconds).
// There are speedier, more elegant solutions on the forum.

class Problem75 extends Problem(75, "214954") {
  // how many elements appear only once?
  def uniqueCount[T](xs: List[T]): Int =
    xs.groupBy(identity).count(_._2.size == 1)
  def solve = {
    // generate only the primitive triples for now (because this formula, if allowed to generate
    // non-primitive triples too, generates some of them, but not all of them!)
    val triples =
      for{m <- Stream.from(1)
          n <- 1 until m
          if n.gcd(m) == 1 && (n + m) % 2 == 1}
      yield List(m * m - n * n, 2 * m * n, m * m + n * n)
    // finds all the triples below the limit, but answer may contain duplicates
    def solutions(limit: Int): Stream[List[Int]] =
      triples.takeWhile(_.exists(_ <= limit))
        .filter(_.sum <= limit)
        // add the non-primitive triples back in
        .flatMap(t => Stream.from(1).map(j => t.map(_ * j))
                                    .takeWhile(_.sum <= limit))
    def lengths(limit: Int) =
      solutions(limit).toList.distinct.map(_.sum)
    uniqueCount(lengths(2000000))
  }
}
