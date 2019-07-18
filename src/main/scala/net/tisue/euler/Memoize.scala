package net.tisue.euler

object Memo
{
  def memoize[A, B](fn: A => B): A => B = {
    val cache = collection.mutable.Map[A, B]()
    (a: A) => cache.getOrElseUpdate(a, fn(a))
  }
  def memoize[A1, A2, B](fn: (A1, A2) => B): (A1, A2) => B = {
    val cache = collection.mutable.Map[(A1, A2), B]()
    (a1: A1, a2: A2) => cache.getOrElseUpdate((a1, a2), fn(a1, a2))
  }
  def memoize[A1, A2, A3, B](fn: (A1, A2, A3) => B): (A1, A2, A3) => B = {
    val cache = collection.mutable.Map[(A1, A2, A3), B]()
    (a1: A1, a2: A2, a3: A3) => cache.getOrElseUpdate((a1, a2, a3), fn(a1, a2, a3))
  }
  def memoize[A1, A2, A3, A4, B](fn: (A1, A2, A3, A4) => B): (A1, A2, A3, A4) => B = {
    val cache = collection.mutable.Map[(A1, A2, A3, A4), B]()
    (a1: A1, a2: A2, a3: A3, a4: A4) => cache.getOrElseUpdate((a1, a2, a3, a4), fn(a1, a2, a3, a4))
  }
}
