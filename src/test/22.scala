package net.tisue.euler

// What is the total of all the name scores in the file?

class Problem22 extends Problem(22, "871198282") {
  def solve = {
    val names = io.Source.fromFile("dat/22.txt").filter(_ != '"').mkString.split(",").toList
    def score(name: String) = name.map(_ - 'A' + 1).sum
    (for ((name, index) <- names.sorted.zipWithIndex)
     yield score(name) * (index + 1)).sum
  }
}
