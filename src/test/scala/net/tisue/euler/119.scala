package net.tisue.euler

class Problem119 extends Problem(119, "248155780267521"):
  def isSolution(n: BigInt) =
    val digitSum = BigInt(n.digits.sum)
    val ns = LazyList.from(2).map(digitSum.pow).takeWhile(_ <= n)
    digitSum > 1 && ns.nonEmpty && n == ns.last
  // Upper bounds for search arrived at by trial and error.  I also wrote stream-of-streams merging
  // code that worked, but ran out of memory after computing only 15 solutions.
  val solutions =
    for base <- BigInt(2) to 70
        exponent <- 2 to 8
        n = base.pow(exponent)
        if isSolution(n)
    yield n
  def solve = (solutions.sorted.distinct)(29)

