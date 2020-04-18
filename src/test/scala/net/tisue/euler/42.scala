package net.tisue.euler

// The first ten triangle numbers are: 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
// By converting each letter in a word to a number corresponding to
// its alphabetical position and adding these values we form a word
// value. For example, the word value for SKY is 19 + 11 + 25 = 55 =
// t10. If the word value is a triangle number then we shall call the
// word a triangle word.
// Using words.txt, a text file containing nearly two-thousand English
// words, how many are triangle words?

class Problem42 extends Problem(42, "162"):
  val triangles = LazyList.from(1).map(n => n * (n + 1) / 2)
  def isTriangle(n: Int) = n == triangles.dropWhile(_ < n).head
  val words = io.Source.fromResource("42.txt").filter(_ != '"').mkString.split(",").toList
  def score(word: String) = word.map(_ - 'A' + 1).sum
  def solve =
    words.count(word => isTriangle(score(word)))

