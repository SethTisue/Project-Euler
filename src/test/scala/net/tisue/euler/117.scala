package net.tisue.euler
import Memo.memoize

// even easier than 114/115/116!

class Problem117 extends Problem(117, "100808458960497"):
  val count: Int => Long = memoize(size =>
    if size < 0 then 0
    else if size == 0 then 1
    else (1 to 4).map(tileSize => count(size - tileSize)).sum)
  def solve = count(50)
