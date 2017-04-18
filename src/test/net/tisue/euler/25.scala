package net.tisue.euler

// What is the first term in the Fibonacci sequence to contain 1000 digits?

class Problem25 extends Problem(25, "4782") {
  def solve = {
    lazy val fibs: Stream[BigInt] =
      BigInt(0) #:: BigInt(1) #:: fibs.zipWith(fibs.tail)(_ + _)
    fibs.takeWhile(_.toString.size < 1000).size
  }
}
