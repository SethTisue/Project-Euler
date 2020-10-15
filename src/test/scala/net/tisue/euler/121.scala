package net.tisue.euler

class Problem121 extends Problem(121, "2269"):
  // the inputs are how many discs of each color I have drawn so far
  def winChance(blue: Int, red: Int): BigRational =
    if blue + red == 15 then
      if blue > red then 1 else 0
    else
      val rounds = blue + red
      val chanceIfBlue = winChance(blue + 1, red) * BigRational(1, rounds + 2)
      val chanceIfRed  = winChance(blue, red + 1) * BigRational(rounds + 1, rounds + 2)
      chanceIfBlue + chanceIfRed
  def solve = winChance(0, 0) match
    case BigRational(n, d) => d / n

