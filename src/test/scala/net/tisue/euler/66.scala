package net.tisue.euler

// Consider quadratic Diophantine equations of the form:
//   x^2 - Dy^2 = 1
// For example, when D=13, the minimal solution in x is 6492 - 131802 = 1.
// It can be assumed that there are no solutions in positive integers when D is square.
// By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
//   3^2 - 2x2^2 = 1
//   2^2 - 3x1^2 = 1
//   9^2 - 5x4^2 = 1
//   5^2 - 6x2^2 = 1
//   8^2 - 7x3^2 = 1
// Hence, by considering minimal solutions in x for D<=7, the largest x is obtained when D=5.
// Find the value of D<=1000 in minimal solutions of x for which the largest value of x is obtained.

// http://en.wikipedia.org/wiki/Pell%27s_equation#Solution_technique tells us that this is really a
// continued fraction problem!  So we need similar code as problem 64.

class Problem66 extends Problem(66, "661"):
  def isSquare(n: Int) =
    val r = math.sqrt(n).toInt
    r * r == n
  def isSolution(x: BigInt, y: BigInt, d: Int) =
    x * x - y * y * d == BigInt(1)
  // presumably the code in this next method could be made more concise
  def continuedFraction(n: Int) =
    val m = math.sqrt(n).toInt
    def iterate(num: Int, c: Int): LazyList[Int] =
      val denom = n - c * c
      val nextA = num * (m + c) / denom
      nextA #:: iterate(denom / num, (nextA * denom - num * c) / num)
    m #:: (if m * m == n then LazyList.empty
           else iterate(1, m))
  def convergents(n: Int) =
    LazyList.from(1).map(len => continuedFraction(n).take(len).map(BigRational(_))
                                  .reduceRight((next, partialResult) => partialResult.reciprocal + next))
  def smallestX(d: Int) =
    convergents(d).find(r => isSolution(r.numer, r.denom, d)).get.numer
  def solve(maxD: Int) =
    (1 to maxD).filter(!isSquare(_)).maxBy(smallestX)
  def solve = solve(1000)

