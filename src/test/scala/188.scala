package net.tisue.euler

// http://en.wikipedia.org/wiki/Modular_exponentiation#Right-to-left_binary_method

// This runs in almost no time.  But some people on the forum noticed that
// the last eight digits stop changing after 1777^^7, so you don't really need
// to go all the way up to 1777^^1855.

class Problem188 extends Problem(188, "95962097"):
  def modpow(b: Long, e: Long, m: Int): Long =
    case class Loop(b: Long, e: Long, result: Long):
      def next = Loop(
        b * b % m,
        e >> 1,
        if (e & 1) == 0 then result
        else result * b % m)
    Iterator.iterate(Loop(b, e, 1))(_.next)
      .dropWhile(_.e > 0)
      .next.result
  def hyper(base: Long, exponent: Int, modulus: Int): Long =
    Iterator.iterate(base)(modpow(base, _, modulus))
      .drop(exponent).next
  def solve = hyper(1777, 1855, 100000000)
