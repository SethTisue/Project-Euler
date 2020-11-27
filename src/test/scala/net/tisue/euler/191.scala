package net.tisue.euler
import Memo.memoize

class Problem191 extends Problem(191, "1918080160"):
  val count: (Int, Int, Boolean) => Int =
    memoize:
      (days, consecutiveAbsents, hasBeenLate) =>
        if consecutiveAbsents >= 3 then 0
        else if days == 30 then 1
        else
          val L = if hasBeenLate then 0 else count(days + 1, 0, true)
          val O = count(days + 1, 0, hasBeenLate)
          val A = count(days + 1, consecutiveAbsents + 1, hasBeenLate)
          L + O + A
  def solve = count(0, 0, false)
