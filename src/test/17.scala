package net.tisue.euler

// If the numbers from 1 to 1000 inclusive is written out in words, how many letters are used?

class Problem17 extends Problem(17, "21124") {
  def english(n: Int): String = {
    if(n == 1000) "onethousand"
    else if(n % 100 == 0) english(n / 100) + "hundred"
    else if(n > 100) english(100 * (n / 100)) + "and" + english(n % 100)
    else if(n < 20) List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                         "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                         "seventeen", "eighteen", "nineteen")(n - 1)
    else if(n % 10 == 0) List("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")(n / 10 - 2)
    else english(10 * (n / 10)) + "" + english(n % 10)
  }
  def solve = (1 to 1000).map(english(_).size).sum
}
