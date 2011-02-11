package net.tisue.euler
import annotation.tailrec

object Euler {

  // measure how long something takes to compute
  def time[T](fn: => T): (T, Double) = {
    val start = System.nanoTime
    (fn, (System.nanoTime - start) / 1.0E9)
  }

  // binary search
  @tailrec def binarySearch(lowerBound: Int, upperBound: Int)(tooBig: Int => Boolean): Int =
    if(lowerBound == upperBound - 1)
      lowerBound
    else {
      val guess = (lowerBound + upperBound + 1) / 2
      if(tooBig(guess))
        binarySearch(lowerBound, guess)(tooBig)
      else
        binarySearch(guess, upperBound)(tooBig)
    }

  // pick n items in order from a longer list, in all possible ways
  def choose[T](n: Int, xs: List[T]): List[List[T]] =
    if(n > xs.size) Nil
    else if(n == 0) List(Nil)
    else choose(n, xs.tail) ::: choose(n - 1, xs.tail).map(xs.head :: _)

  // permutations of a list of unique items.
  // CAUTION: doesn't work on lists containing repeats
  def permute[A](xs: List[A]): List[List[A]] = xs match {
    case Nil => List(Nil)
    case _ => xs.flatMap(x => permute(xs.filter(_ != x)).map(x :: _))
  }

  def unfold[T1,T2](x: T1)(fn: T1 => Option[(T2, T1)]): Stream[T2] =
    fn(x) match {
      case None => Stream()
      case Some((result, next)) => result #:: unfold(next)(fn)
    }

  // Haskell has these, dunno why they're not in Scala. some of these
  // could go on Iterable, actually, instead of Stream specifically
  implicit def toRichStream[T](s1: Stream[T]): RichStream[T] = new RichStream(s1)
  class RichStream[T1](s1: Stream[T1]) {
    // I would like axe this and replace it everywhere with (s1, s2).zipped.map(...),
    // but I can't because of this ticket:
    // lampsvn.epfl.ch/trac/scala/ticket/2634 (closed as wontfix) - ST 11/16/09
    def zipWith[T2,T3](s2: Stream[T2])(fn: (T1,T2)=>T3): Stream[T3] =
      s1.zip(s2).map(fn.tupled)
    def circular: Stream[T1] = {
      lazy val s: Stream[T1] = s1 #::: s
      s
    }
    def group: Stream[Stream[T1]] =
      unfold(s1){s1 =>
        if(s1.isEmpty) None
        else Some(s1.span(_ == s1.head))}
  }

  // add gcd method to Int
  implicit def toRichInt(i: Int): MyRichInt = new MyRichInt(i)
  class MyRichInt(i: Int) {
    // perhaps there's some better way on both of these than going through String?
    def gcd(j: Int) = (BigInt(i) gcd BigInt(j)).toString.toInt
    def digits = i.toString.view.map(_.asDigit).toList
  }

  // add digits method to Long
  implicit def toRichLong(i: Long): MyRichLong = new MyRichLong(i)
  class MyRichLong(i: Long) {
    // perhaps there's some better way than going through String?
    def digits = i.toString.view.map(_.asDigit).toList
  }

  // add digits method to BigInt
  implicit def toRichBigInt(i: BigInt): MyRichBigInt = new MyRichBigInt(i)
  class MyRichBigInt(i: BigInt) {
    // perhaps there's some better way than going through String?
    def digits = i.toString.view.map(_.asDigit).toList
  }

}

/*
code I might want in the future:

  // first, stream merging stuff. based on the Haskell code at
  // www.cs.dartmouth.edu/~cs8/W2009/lectures/streamsCont.txt
  // note that for this particular application, we want to keep
  // duplicates, not discard them.
  def merge[T](s1: Stream[T], s2: Stream[T])(implicit o: Ordering[T]) : Stream[T] =
    if(s1.isEmpty) s2
    else if(s2.isEmpty) s1
    else o.compare(s1.head, s2.head) match {
      case -1 => s1.head #:: merge(s1.tail, s2)
      case  _ => s2.head #:: merge(s1, s2.tail)
    }
  def mergeMerge[T](xss: Stream[Stream[T]])(implicit o: Ordering[T]): Stream[T] = {
    def helper(items: Stream[T], streams: Stream[Stream[T]]): Stream[T] =
      items.head #:: helper(merge(items.tail, streams.head), streams.tail)
    helper(xss.head, xss.tail)
  }
*/
