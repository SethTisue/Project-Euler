package net.tisue.euler
import Euler._

class Problem121 extends Problem(121, "2269") {
  // the inputs are how many discs of each color I have drawn so far
  def winChance(blue: Int, red: Int): BigRational =
    if(blue + red == 15)
      if(blue > red) 1 else 0
    else {
      val rounds = blue + red
      val chanceIfBlue = winChance(blue + 1, red) * new BigRational(1, rounds + 2)
      val chanceIfRed  = winChance(blue, red + 1) * new BigRational(rounds + 1, rounds + 2)
      chanceIfBlue + chanceIfRed
    }
  def solve = winChance(0, 0) match {
    case BigRational(n, d) => d / n
  }
}
