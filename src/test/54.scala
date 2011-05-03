package net.tisue.euler
import Euler._

// The input file contains one thousand random hands dealt to two players.  How many hands does
// player one win?

// For beautiful code, see glguy's Haskell solution below.

class Problem54 extends Problem(54, "376") {
  case class Card(rank: Int, suit: Char)
  type Hand = List[Card]
  // for example if hand is 9-9-8-8-7, result is List((2, 9), (2, 8), (1, 7))
  // (so we can use Ordering[Iterable[(Int,Int)]] to compare two hands)
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
    def straightFlush(hand: Hand) = straight(hand) && flush(hand)
    def nOfAKind(hand: Hand, n: Int) = groupSizes(hand).head >= n
    def fullHouse(hand: Hand) = groupSizes(hand) == List(3, 2)
    def flush(hand: Hand) = hand.map(_.suit).toSet.size == 1
    def twoPairs(hand: Hand) = groupSizes(hand) == List(2, 2, 1)
    def straight(hand: Hand) =
      hand.map(_.rank)
          .sorted
          .sliding(2)
          .forall{case Seq(r1, r2) => r2 == r1 + 1}
    val handFunctions =
      List(straightFlush _,
           nOfAKind(_: Hand, 4),
           fullHouse _,
           flush _,
           straight _,
           nOfAKind(_: Hand, 3),
           twoPairs _,
           nOfAKind(_: Hand, 2),
           nOfAKind(_: Hand, 1))
    handFunctions.size - handFunctions.indexWhere(_(hand))
  }
  def beats(hand1: Hand, hand2: Hand): Boolean = {
    def score(hand: Hand) = (handType(hand), groups(hand))
    val x = Ordering[(Int,Iterable[(Int, Int)])]; import x._
    score(hand1) > score(hand2)
  }
  def solve = {
    val input: List[(Hand,Hand)] = {
      def readCard(s: String) = Card("23456789TJQKA".indexOf(s(0)), s(1))
      for{line <- io.Source.fromFile("dat/54.txt").getLines.toList
          cards = line.split(" ").toList.map(readCard)}
      yield cards.splitAt(5)
    }
    input.count((beats _).tupled)
  }
}

/* glguy's Haskell solution, from the Project Euler forum:

module Main where
import Data.List
import Data.Maybe
import Control.Monad
 
readCard [r,s] = (parseRank r, parseSuit s)
 where parseSuit = translate "SHDC"
       parseRank = translate "23456789TJQKA"
       translate from x = fromJust $ findIndex (==x) from
 
solveHand hand = (handRank,tiebreak)
 where
 handRank
  | flush && straight   = 9
  | hasKinds 4          = 8
  | all hasKinds [2,3]  = 7
  | flush               = 6
  | straight            = 5
  | hasKinds 3          = 4
  | 1 < length (kind 2) = 3
  | hasKinds 2          = 2
  | otherwise           = 1
 tiebreak = kind =<< [4,3,2,1]
 hasKinds = not . null . kind
 kind n = map head $ filter ((n==).length) $ group ranks
 ranks  = reverse $ sort $ map fst hand
 flush  = 1 == length (nub (map snd hand))
 straight = length (kind 1) == 5 && 4 == head ranks - last ranks
 
gameLineToHands = splitAt 5 . map readCard . words
p1won (a,b) = solveHand a > solveHand b
 
main = do
    f <- readFile "poker.txt"
    let games = map gameLineToHands $ lines f
        wins = filter p1won games
    print $ length wins

*/
