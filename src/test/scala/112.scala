package net.tisue.euler

// If no digit is exceeded by the digit to its left it is called an increasing number; for example,
// 134468.  Similarly if no digit is exceeded by the digit to its right it is called a decreasing
// number; for example, 66420.  We shall call a positive integer that is neither increasing nor
// decreasing a "bouncy" number; for example, 155349. The least number for which the proportion of
// bouncy numbers first exceeds 50% is 538.  By 21780 the proportion of bouncy numbers is equal to
// 90%.  Find the least number for which the proportion of bouncy numbers is exactly 99%.

class Problem112 extends Problem(112, "1587000"):
  def isIncreasing(n: Int): Boolean =
    n < 10 || n % 10 >= (n % 100) / 10 && isIncreasing(n / 10)
  def isDecreasing(n: Int): Boolean =
    n < 10 || n % 10 <= (n % 100) / 10 && isDecreasing(n / 10)
  def isBouncy(n: Int) =
    !isIncreasing(n) && !isDecreasing(n)
  def solve(min: Double) =
    // by using a recursive helper function we achieve pure-functionality,
    // but I think the imperative version (see previous rev in svn) is
    // more readable. I'd like to know if this can be both pure and elegant.
    def helper(bouncies: Int, n: Int): Int =
      if n > 0 && bouncies.toDouble / n == min then
        n
      else
        helper(
          if isBouncy(n + 1)
          then bouncies + 1
          else bouncies,
          n + 1)
    helper(0, 0)
  def solve = solve(0.99)
