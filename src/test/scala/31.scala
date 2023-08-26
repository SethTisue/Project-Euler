package net.tisue.euler

// In England there are eight coins in general circulation:
//   1p, 2p, 5p, 10p, 20p, 50p, 1 pound (100p), 2 pound (200p)
// How many different ways can 2 pounds be made using any number of coins?

// Note that since the last coin is a penny, we never get into
// a situation where we can't make change.

class Problem31 extends Problem(31, "73682"):
  def solve =
    def change(n: Int, coins: List[Int]): Int =
      if n == 0 then 1
      else if n < 0 || coins == Nil then 0
      else change(n, coins.tail) + change(n - coins.head, coins)
    change(200, List(200, 100, 50, 20, 10, 5, 2, 1))
