package net.tisue.euler

import annotation.tailrec

// Find the largest palindrome made from the product of two 3-digit numbers.

// This is easily solved in under a second by pure brute force, but here I'm trying to make it run
// as fast as possible by exiting early whenever possible.

trait Problem4Helpers:
  def isPalindrome(k: Int) =
    k.toString == k.toString.reverse

// imperative version
class Problem4 extends Problem(4, "906609") with Problem4Helpers:
  def solve: Int =
    var winner = 0
    var a = 999
    while a >= 100 do
      var p = a * a
      if p <= winner then
        return winner
      while p > winner do
        if isPalindrome(p) then
          winner = p
          p = 0
        else
          p -= a
      a -= 1
    end while
    winner

// functional version
class Problem4f extends Problem(4, "906609") with Problem4Helpers:
  def solve =
    def loop1(a: Int, winner: Int): Int =
      if a < 100 || a * a <= winner then winner
      else
        @tailrec def loop2(p: Int): Int =
          if p <= winner then loop1(a - 1, winner)
          else if isPalindrome(p) then loop1(a - 1, p)
          else loop2(p - a)
        loop2(a * a)
    loop1(999, 0)

// surely this can be expressed more elegantly somehow?
