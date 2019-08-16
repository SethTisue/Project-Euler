package net.tisue.euler

// Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
// where each _ is a single digit.

object Problem206 extends Problem(206, "1389019170") {
  def solve = {
     // reduces to finding a square 1_2_3_4_5_6_7_8_9 since the last _ above must be 0.
    val range = math.sqrt(10203040506070809L).toInt to
                math.sqrt(19293949596979899L).toInt
    val regex = java.util.regex.Pattern.compile("1.2.3.4.5.6.7.8.9")
    10 * range.find(n => regex.matcher((n.toLong * n).toString).matches).get
  }
}
