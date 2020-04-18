package net.tisue.euler

// A common security method used for online banking is to ask the user for three random characters
// from a passcode. For example, if the passcode was 531278, they may ask for the 2nd, 3rd, and
// 5th characters; the expected reply would be: 317.
// The text file, keylog.txt, contains fifty successful login attempts.
// Given that the three characters are always asked for in order, analyse the file so as to
// determine the shortest possible secret passcode of unknown length.

// Note: I don't know if my algorithm is actually guaranteed to find the right answer, or if I just
// got lucky...!  I may have just been lucky.  If I don't sort the keys first I don't get the right
// answer, so that suggests I was lucky.  However, note that once my code produced an answer I knew
// it was correct because it had no repeated digits.

// I could also have just guessed that the answer contains no repeated digits, then tried all the
// combinations.

// Notwithstanding all of the above, the following code may be amusing to someone.  It generates a
// very long solution by concatenating all the keys (after removing duplicates), and then tries to
// make it shorter by removing unneeded digits one at a time.

class Problem79 extends Problem(79, "73162890"):
  val keylog = List(319, 680, 180, 690, 129, 620, 762, 689, 762, 318, 368, 710, 720, 710, 629, 168, 160,
                    689, 716, 731, 736, 729, 316, 729, 729, 710, 769, 290, 719, 680, 318, 389, 162, 289,
                    162, 718, 729, 319, 790, 680, 890, 362, 319, 760, 316, 729, 380, 319, 728, 716)
    .distinct.sorted.map(_.toString)
  def contains(a: String, b: String): Boolean =
    b.isEmpty || !a.isEmpty && (if a.head == b.head then contains(a.tail, b.tail)
                                else contains(a.tail, b))
  def nextAnswer(s: String): Option[String] =
    (0 until s.size).map(j => (s.take(j) ++ s.drop(j + 1)).mkString)
      .find(s => keylog.forall(contains(s, _)))
  def iterate(guess: String): String =
    nextAnswer(guess).map(iterate).getOrElse(guess)
  def solve = iterate(keylog.mkString)

