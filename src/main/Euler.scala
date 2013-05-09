package net.tisue

import language.higherKinds
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

  def unfold[T1, T2](x: T1)(fn: T1 => Option[(T2, T1)]): Stream[T2] =
    fn(x) match {
      case None => Stream()
      case Some((result, next)) => result #:: unfold(next)(fn)
    }

  // Haskell has these, dunno why they're not in Scala. some of these
  // could go on Iterable, actually, instead of Stream specifically.
  implicit class RichStream[T](s: Stream[T]) {
    def circular: Stream[T] = {
      lazy val circle: Stream[T] = s #::: circle
      circle
    }
    def group: Stream[Stream[T]] =
      unfold(s){s =>
        if(s.isEmpty) None
        else Some(s.span(_ == s.head))}
  }

  // add gcd, digits methods to Int
  implicit class RichInt(val i: Int) extends AnyVal {
    def gcd(j: Int): Int =
      if(j == 0) i
      else j.gcd(i - j * (i / j))
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to Long
  implicit class RichLong(val i: Long) extends AnyVal {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to BigInt
  implicit class RichBigInt(val i: BigInt) extends AnyVal {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add zipWith to Iterable; thank you stackoverflow.com/q/3895813
  implicit class RichIterable[A, CC[X] <: Iterable[X]](xs: CC[A]) {
    type Builder[C] = collection.generic.CanBuildFrom[Nothing, C, CC[C]]
    def zipWith[B, C](ys: Iterable[B])(f: (A, B) => C)(implicit cbf: Builder[C]): CC[C] =
      xs.zip(ys).map(f.tupled)(collection.breakOut)
  }

}
