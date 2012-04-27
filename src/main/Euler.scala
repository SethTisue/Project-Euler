package net.tisue
import language.{ higherKinds, implicitConversions }
import annotation.tailrec

package object euler {

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

  def unfold[T1,T2](x: T1)(fn: T1 => Option[(T2, T1)]): Stream[T2] =
    fn(x) match {
      case None => Stream()
      case Some((result, next)) => result #:: unfold(next)(fn)
    }

  implicit def toRichStream[T](s1: Stream[T]): RichStream[T] = new RichStream(s1)
  implicit def toRichIterable[CC[X] <: Iterable[X], A](xs: CC[A]) = new RichIterable[A, CC](xs)
  implicit def toRichInt(i: Int): MyRichInt = new MyRichInt(i)
  implicit def toRichLong(i: Long): MyRichLong = new MyRichLong(i)
  implicit def toRichBigInt(i: BigInt): MyRichBigInt = new MyRichBigInt(i)

}

package euler {

  // Haskell has these, dunno why they're not in Scala. some of these
  // could go on Iterable, actually, instead of Stream specifically
  class RichStream[T1](s1: Stream[T1]) {
    def circular: Stream[T1] = {
      lazy val s: Stream[T1] = s1 #::: s
      s
    }
    def group: Stream[Stream[T1]] =
      unfold(s1){s1 =>
        if(s1.isEmpty) None
        else Some(s1.span(_ == s1.head))}
  }

  // thank you stackoverflow.com/questions/3895813/how-to-write-a-zipwith-method-that-returns-the-same-type-of-collection-as-those-p
  // maybe I could use some of this same magic to make some of my other implicits work on more collection types!
  class RichIterable[A, CC[X] <: Iterable[X]](xs: Iterable[A]) {
    import scala.collection.generic.CanBuildFrom
    def zipWith[B, C](ys: Iterable[B])(f: (A, B) => C)(implicit cbf: CanBuildFrom[Nothing, C, CC[C]]): CC[C] =
      xs.zip(ys).map(f.tupled)(collection.breakOut)
  }

  // add gcd method to Int
  class MyRichInt(i: Int) {
    def gcd(j: Int): Int =
      if(j == 0) i
      else j.gcd(i - j * (i / j))
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to Long
  class MyRichLong(i: Long) {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to BigInt
  class MyRichBigInt(i: BigInt) {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
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
