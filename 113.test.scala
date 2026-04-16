package net.tisue.euler
import Memo.memoize

// How many numbers below a googol (10^100) are not bouncy?
// (see problem 112 for definition)

class Problem113 extends Problem(113, "51161058134250"):
  // To count decreasing numbers, we recurse on how many digits of room we have left and how
  // low the digits so far have sunk.  Memoizing keeps it efficient.
  val decreasings: (Int, Int) => Long =
    memoize((maxDigits, highestAllowed) =>
      if maxDigits == 0 then 0
      else (0 to highestAllowed).map(1 + decreasings(maxDigits - 1, _)).sum)
  // Every increasing number is just a decreasing number reversed, but some decreasing
  // numbers have trailing zeros, which become leading zeros when reversed, so we have
  // to subtract those out.
  def increasings(maxDigits: Int) =
    decreasings(maxDigits, 9) - decreasings(maxDigits - 1, 9) - 1
  def solve =
    // Some numbers are both increasing and decreasing, so we need a further correction
    // for that.
    def solve(maxDigits: Int) =
      increasings(maxDigits) + decreasings(maxDigits, 9) - 10 * maxDigits
    solve(100)

// The output of "increasings" is A035927 in the Online Encyclopedia of Integer Sequences; it's
// just binomial coefficients.  That could be a basis for a very direct solution.
