package net.tisue.euler

// The input file contains one thousand random poker hands dealt to two players.  How many hands
// does player one win?

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
  def handType(hand: Hand) = {
    def straightFlush(hand: Hand) =
      straight(hand) && flush(hand)
    def nOfAKind(hand: Hand, n: Int) =
      groupSizes(hand).head >= n
    def fullHouse(hand: Hand) =
      groupSizes(hand) == List(3, 2)
    def flush(hand: Hand) =
      hand.map(_.suit).toSet.size == 1
    def twoPairs(hand: Hand) =
      groupSizes(hand) == List(2, 2, 1)
    def straight(hand: Hand) =
      hand.map(_.rank)
          .sorted
          .sliding(2)
          .forall{case Seq(r1, r2) => r2 == r1 + 1}
    val handFunctions: List[Hand => Boolean] =
      List(straightFlush _,
           nOfAKind(_, 4),
           fullHouse _,
           flush _,
           straight _,
           nOfAKind(_, 3),
           twoPairs _,
           nOfAKind(_, 2),
           nOfAKind(_, 1))
    handFunctions.size - handFunctions.indexWhere(_(hand))
  }

  def beats(hand1: Hand, hand2: Hand): Boolean = {
    def score(hand: Hand) = (handType(hand), groups(hand))
    val x = Ordering[(Int, Iterable[(Int, Int)])]; import x._
    score(hand1) > score(hand2)
  }

  def solve = {
    val input: List[(Hand, Hand)] = {
      def readCard(s: String) = Card("23456789TJQKA".indexOf(s(0)), s(1))
      for{line <- io.Source.fromFile("dat/54.txt").getLines.toList
          cards = line.split(" ").toList.map(readCard)}
      yield cards.splitAt(5)
    }
    input.count((beats _).tupled)
  }

}
