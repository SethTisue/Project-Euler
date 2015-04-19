package net.tisue.euler

// The input file contains one thousand random poker hands dealt to two players.
// How many hands does player one win?

// (note: glguy's Haskell solution on the Euler forum is more elegant)

class Problem54 extends Problem(54, solution = "376") {

  case class Card(rank: Int, suit: Char)
  type Hand = List[Card]

  // for example if hand is 9-9-8-8-7 (in any order), result is List((2, 9), (2, 8), (1, 7)) (so we
  // can use Ordering[Iterable[(Int, Int)]] to compare two hands)
  def groups(hand: Hand): Seq[(Int, Int)] =
    hand.groupBy(_.rank)
      .mapValues(_.size)
      .toList
      .map(_.swap)
      .sortBy(-_._2)
      .sortBy(-_._1)

  // for example if hand is 9-9-8-8-7, result is List(2, 2, 1)
  def groupSizes(hand: Hand) =
    groups(hand).map(_._1)

  // returns 0 if it's 1 of a kind, 1 if it's 2 of a kind, 2 for 2 pairs, and so on
  def handKind(hand: Hand) = {
    def isStraightFlush(hand: Hand) =
      isStraight(hand) && isFlush(hand)
    def isNOfAKind(hand: Hand, n: Int) =
      groupSizes(hand).head >= n
    def isFullHouse(hand: Hand) =
      groupSizes(hand) == List(3, 2)
    def isFlush(hand: Hand) =
      hand.map(_.suit).toSet.size == 1
    def isTwoPairs(hand: Hand) =
      groupSizes(hand) == List(2, 2, 1)
    def isStraight(hand: Hand) =
      hand.map(_.rank)
        .sorted
        .sliding(2)
        .forall{case Seq(r1, r2) => r2 == r1 + 1}
    val handFunctions: List[Hand => Boolean] =
      List(isStraightFlush _,
           isNOfAKind(_, 4),
           isFullHouse _,
           isFlush _,
           isStraight _,
           isNOfAKind(_, 3),
           isTwoPairs _,
           isNOfAKind(_, 2),
           isNOfAKind(_, 1))
    handFunctions.size - handFunctions.indexWhere(_(hand))
  }

  def beats(hand1: Hand, hand2: Hand): Boolean = {
    type Score = (Int, Iterable[(Int, Int)])
    def score(hand: Hand): Score =
      (handKind(hand), groups(hand))
    // tuples get ordered fieldwise, so we can just:
    Ordering[Score].gt(score(hand1), score(hand2))
  }

  def solve = {
    val input: List[(Hand, Hand)] = {
      def readCard(s: String) =
        Card("23456789TJQKA".indexOf(s(0)), s(1))
      for {
        line <- io.Source.fromFile("dat/54.txt").getLines.toList
        cards = line.split(" ").toList.map(readCard)
      }
      yield cards.splitAt(5)
    }
    input.count{case (hand1, hand2) =>
      beats(hand1, hand2)}
  }

}
