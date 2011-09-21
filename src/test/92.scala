package net.tisue.euler

// A number chain is created by continuously adding the square of the digits in a number to form a
// new number until it has been seen before.
// For example,
// 44 -> 32 -> 13 -> 10 -> 1 -> 1
// 85 -> 89 -> 145 -> 42 -> 20 -> 4 -> 16 -> 37 -> 58 -> 89
// Therefore any chain that arrives at 1 or 89 will become stuck in an endless loop. What is most
// amazing is that EVERY starting number will eventually arrive at 1 or 89.
// How many starting numbers below ten million will arrive at 89?

// 9 seconds on my iMac.  Making it faster would involve being smart about only testing each
// combination of digits once.

class Problem92 extends Problem(92, "8581146") {
  def next(n: Int) = n.digits.map(d => d * d).sum
  def chain(n: Int) = Stream.iterate(n)(next)
  def solve = {
    val memory = Array.fill(1000)(false)
    for(n <- 1 until 1000)
      memory(n) = chain(n).find(n => n == 1 || n == 89).get == 89
    (1 until 10000000).count(n => memory(next(n)))
  }
}
