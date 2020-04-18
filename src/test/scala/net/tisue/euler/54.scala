package net.tisue.euler

// The input file contains one thousand random poker hands dealt to two players.
// How many hands does player one win?

// (note: glguy's Haskell solution on the Euler forum is more elegant)

class Problem54 extends Problem(54, solution = "376"):

  case class Card(rank: Int, suit: Char)
  type Hand = List[Card]

  // We'll often need to know what groups of same-ranked cards exist.
  // We'll sort the groups in descending order by size, then by rank.
  // So for example for the hand 9-9-8-8-7 (in any order), the result
  // here is Seq((2, 9), (2, 8), (1, 7)).
  def groups(hand: Hand): Seq[(Int, Int)] =
    hand.groupBy(_.rank)
      .view.mapValues(_.size)
      .toList
      .map(_.swap)
      .sortBy(-_._2)
      .sortBy(-_._1)

  // sometimes we only care what size the groups are.
  // for example if hand is 9-9-8-8-7, result here is Seq(2, 2, 1)
  def groupSizes(hand: Hand): Seq[Int] =
    groups(hand).map(_._1)

  // higher-is-better return value, so:
  //   0 for 1 of a kind
  //   1 for 2 of a kind
  //   2 for 2 pairs
  //   ...
  def handKind(hand: Hand): Int =
    def isStraightFlush =
      isStraight && isFlush
    def isNOfAKind(n: Int) =
      groupSizes(hand).head >= n
    def isFullHouse =
      groupSizes(hand) == List(3, 2)
    def isFlush =
      hand.map(_.suit).distinct.size == 1
    def isTwoPairs =
      groupSizes(hand) == List(2, 2, 1)
    def isStraight =
      hand.map(_.rank)
        .sorted
        .sliding(2)
        .forall{case Seq(r1, r2) : Seq[Int] @unchecked => r2 == r1 + 1}
    val handFunctions =
      IndexedSeq(
        () => isNOfAKind(1),
        () => isNOfAKind(2),
        () => isTwoPairs,
        () => isNOfAKind(3),
        () => isStraight,
        () => isFlush,
        () => isFullHouse,
        () => isNOfAKind(4),
        () => isStraightFlush)
    handFunctions.lastIndexWhere(_.apply)

  def beats(hand1: Hand, hand2: Hand): Boolean =
    type Score = (Int, Seq[(Int, Int)])
    def score(hand: Hand): Score =
      (handKind(hand), groups(hand))
    // tuples get ordered fieldwise, so we can just:
    import Ordering.Implicits._
    Ordering[Score].gt(score(hand1), score(hand2))

  def solve =
    val input: List[(Hand, Hand)] =
      def readCard(s: String) =
        Card("23456789TJQKA".indexOf(s(0)), s(1))
      for line <- io.Source.fromResource("54.txt").getLines.toList
          cards = line.split(" ").toList.map(readCard)
      yield cards.splitAt(5)
    input.count{case (hand1, hand2) =>
      beats(hand1, hand2)}
