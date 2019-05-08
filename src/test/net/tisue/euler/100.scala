package net.tisue.euler

// If a box contains fifteen blue discs and six red discs and two discs are taken at random the
// probability of taking two blue discs is (15/21)(14/20) = 1/2.  The next such arrangement
// is 85 blue and 35 red: (85/120)(84/119) = 1/2.  How many blue discs are in the first
// such arrangement with more than 10^12 discs total?

// We're looking for a triangular number which is exactly double another triangular number.
// (Someone in the forum says problem 94 involves that too, but at present I haven't looked
// at problem 94 yet.)

// I had a very strong hunch this had something to do with the convergents for the square root of
// two.  I was right!  But after a few hours I still hadn't found the connection, so I wrote simple
// code to generate small solutions which I then looked up in the On-Line Encyclopedia of Integer
// Sequences.  http://www.research.att.com/~njas/sequences/A011900 has the needed math.  I used
// Richard Choulet's formula since it's the most direct.  (I could also have used my
// convergents code from problem 66 to generate A001653.)

class Problem100 extends Problem(100, "756872327473") {
  def square(n: BigInt) = n * n
  def sqrt(n: BigInt) = {
    def next(guess: BigInt) = (guess + n / guess) / 2
    LazyList.iterate(n)(next).find(square(_) <= n).get
  }
  def solve = {
    def next(n: BigInt) = n * 3 - 1 + sqrt(n * n * 8 - n * 8 + 1)
    val solutions = LazyList.iterate(BigInt(1))(next)
    solutions.takeWhile(_ < BigInt("1000000000000")).last
  }
}
