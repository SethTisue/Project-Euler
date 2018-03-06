package net.tisue.euler

object Problem173 extends Problem(173, "1572729") {
  val limit = 1000000
  def isSolution(outerDim: Int, innerDim: Int) =
    (outerDim + innerDim) * (outerDim - innerDim) <= limit
  def countSolutions(outerDim: Int) =
    ((outerDim - 2) to 1 by -2).takeWhile(isSolution(outerDim, _)).size
  def solve =
    (3 to (limit / 4 + 1)).map(countSolutions).sum
}
