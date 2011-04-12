package net.tisue.euler
import Euler._
import Memo.memoize

class Problem191 extends Problem(191, "1918080160") {
  val count: (Int, Int, Boolean) => Int =
    memoize((days, consecutiveAbsents, hasBeenLate) =>
      if(consecutiveAbsents >= 3) 0
      else if(days == 30) 1
      else {
        val L = if(hasBeenLate) 0 else count(days + 1, 0, true)
        val O = count(days + 1, 0, hasBeenLate)
        val A = count(days + 1, consecutiveAbsents + 1, hasBeenLate)
        L + O + A
      })
  def solve = count(0, 0, false)
}
