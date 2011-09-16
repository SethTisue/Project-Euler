package net.tisue.euler

// To encrypt a text file, convert the bytes to ASCII, then XOR each byte with a given value, taken
// from a secret key.  If the key is shorter than the message, the key is repeated cyclically
// throughout the message.  Your task has been made easy, as the encryption key consists of three
// lower case characters. Using euler59.txt (a file containing the encrypted ASCII codes), and the
// knowledge that the plain text must contain common English words, decrypt the message and find the
// sum of the ASCII values in the original text.

class Problem59 extends Problem(59, "107359") {
  // Using an actual English dictionary is arguably overkill; we could use heuristics instead.
  // But using a dictionary is easy enough, and runs fast enough.
  val dict = io.Source.fromFile("/usr/share/dict/words")("ISO-8859-1")
               .getLines.map(_.trim.toLowerCase).toSet
  def isEnglish(s: String): Boolean = {
    val words = s.toLowerCase.filter((c:Char) => c == ' ' || c.isLetter).mkString.split(' ')
    0.5 < words.count(dict.contains(_)) / words.size.toDouble
  }
  def decrypt(s: String, key: Seq[Char]) =
    s.toStream.zip(key.toStream.circular)
     .map{case (c1, c2) => c1 ^ c2}.map(_.toChar).mkString
  val cipherText =
    io.Source.fromFile("dat/59.txt").mkString.trim.split(",").map(_.toInt.toChar).mkString
  val key =
    (for{c1 <- 'a' to 'z'; c2 <- 'a' to 'z'; c3 <- 'a' to 'z'}
     yield List(c1, c2, c3))
    .find(key => isEnglish(decrypt(cipherText, key)))
    .get
  def solve =
    decrypt(cipherText, key).view.map(_.toInt).sum
}
