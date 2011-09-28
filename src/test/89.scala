package net.tisue.euler

// The input file contains one thousand numbers written in valid, but not necessarily minimal, Roman
// numerals.  Find the number of characters saved by writing each of these in their minimal form.

class Problem89 extends Problem(89, "743") {
  val key = List(
    "M" -> 1000, "CM" -> 900, "D" -> 500, "CD" -> 400, "C" -> 100, "XC" -> 90,
    "L" -> 50, "XL" -> 40, "X" -> 10, "IX" -> 9, "V" -> 5, "IV" -> 4, "I" -> 1)
  def arabic(s: String): Int =
    key.collectFirst{
      case (letters, number) if s.startsWith(letters) =>
        number + arabic(s.drop(letters.size))
    }.getOrElse(0)
  def roman(n: Int): String =
    key.collectFirst{
      case (letters, number) if number <= n =>
        letters + roman(n - number)
    }.getOrElse("")
  def solve =
    io.Source.fromFile("dat/89.txt")
      .getLines.map(_.trim)
      .map(r => r.size - roman(arabic(r)).size)
      .sum
}
