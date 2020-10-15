package net.tisue.euler

// Find all the square anagram word pairs (e.g. CARE 1296 = 36^2, RACE 9216 = 96^2).
// What is the largest square number formed by any member of such a pair?

class Problem98 extends Problem(98, "18769"):
  val words: Iterable[String] =
    io.Source.fromResource("98.txt").mkString.trim.split(",")
      .map(_.drop(1).dropRight(1).mkString)
  val anagrams: Iterable[List[String]] =
    words.groupBy(_.toSeq.sorted.mkString).values.map(_.toList).filter(_.size > 1)
  val squares = LazyList.from(1).map(n => n * n)
  def squaresOfLength(n: Int) =
    squares
      .dropWhile(_.toString.size < n)
      .takeWhile(_.toString.size == n)
  // e.g. scramble(1296, "CARE", "RACE") => 9216
  def scramble(n: Int, word1: String, word2: String): Option[Int] =
    val substitutions = (word1 zip n.toString).toMap
    // "a letter [may not] have the same digital value as another letter"
    if substitutions.size == substitutions.values.toSet.size then
      Some(word2.map(substitutions(_)).mkString.toInt)
    else None
  // slight loss of generality here: assume there are no anagram triples in the input. in the actual
  // input there is one triple but the words are short so we can ignore it.
  val solutions =
    for case List(word1, word2) <- anagrams ++ anagrams.map(_.reverse)
        squares = squaresOfLength(word1.size).toSet
        s <- squares
        scrambled <- scramble(s, word1, word2)
        if s != scrambled && squares.contains(scrambled)
    yield s
  def solve = solutions.max
