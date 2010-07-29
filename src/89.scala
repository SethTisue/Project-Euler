package net.tisue.euler
import Euler._

// The input file contains one thousand numbers written in valid, but not necessarily minimal, Roman
// numerals.  Find the number of characters saved by writing each of these in their minimal form.

class Problem89 extends Problem(89, "743") {
  def solve = {
     val key = List(("M",1000),("CM",900),("D",500),("CD",400),("C",100),("XC",90),
                   ("L",50),("XL",40),("X",10),("IX",9),("V",5),("IV",4),("I",1))
    def arabic(s:String):Int =
      key.find(pair => s.startsWith(pair._1)) match {
        case Some((letters,number)) => number + arabic(s.drop(letters.size))
        case None => 0
      }
    def roman(n:Int):String =
      key.find(_._2 <= n) match {
        case Some((letters,number)) => letters + roman(n - number)
        case None => ""
      }
    io.Source.fromFile("dat/89.txt").getLines.map(_.trim)
      .map(r => r.size - roman(arabic(r)).size).toSeq.sum
  }
}
