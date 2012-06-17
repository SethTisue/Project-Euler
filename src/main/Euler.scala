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

  // Haskell has these, dunno why they're not in Scala. some of these
  // could go on Iterable, actually, instead of Stream specifically
  implicit class RichStream[T1](s1: Stream[T1]) {
    def circular: Stream[T1] = {
      lazy val s: Stream[T1] = s1 #::: s
      s
    }
    def group: Stream[Stream[T1]] =
      unfold(s1){s1 =>
        if(s1.isEmpty) None
        else Some(s1.span(_ == s1.head))}
  }

  // add gcd, digits methods to Int
  implicit class RichInt(i: Int) {
    def gcd(j: Int): Int =
      if(j == 0) i
      else j.gcd(i - j * (i / j))
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to Long
  implicit class RichLong(i: Long) {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add digits method to BigInt
  implicit class RichBigInt(i: BigInt) {
    def digits: Seq[Int] = i.toString.map(_.asDigit)
  }

  // add zipWith to Iterable; thank you stackoverflow.com/q/3895813
  implicit class RichIterable[A, CC[X] <: Iterable[X]](xs: CC[A]) {
    type Builder[C] = collection.generic.CanBuildFrom[Nothing, C, CC[C]]
    def zipWith[B, C](ys: Iterable[B])(f: (A, B) => C)(implicit cbf: Builder[C]): CC[C] =
      xs.zip(ys).map(f.tupled)(collection.breakOut)
  }

}
