package net.tisue.euler

import language.implicitConversions

/// from Programming in Scala book, but converted to use BigInt

class BigRational(n: BigInt, d: BigInt) {
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  private val Precision = 17
  val numer = n / g
  val denom = d / g
  def this(n: BigInt) = this(n, 1)
  def +(that: BigRational): BigRational =
    new BigRational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  def +(i: BigInt): BigRational =
    new BigRational(numer + i * denom, denom)
  def -(that: BigRational): BigRational =
    new BigRational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  def -(i: BigInt): BigRational =
    new BigRational(numer - i * denom, denom)
  def *(that: BigRational): BigRational =
    new BigRational(numer * that.numer, denom * that.denom)
  def *(i: BigInt): BigRational =
    new BigRational(numer * i, denom)
  def /(that: BigRational): BigRational =
    new BigRational(numer * that.denom, denom * that.numer)
  def /(i: BigInt): BigRational =
    new BigRational(numer, denom * i)
  def reciprocal: BigRational =
    new BigRational(denom, numer)
  override def toString: String =
    s"$numer/$denom"
  def toDouble: Double = {
    def div(d1: BigDecimal, d2: BigDecimal) =  // drop down to java.math.BigDecimal
      new BigDecimal(d1.bigDecimal.divide(d2.bigDecimal, Precision, java.math.RoundingMode.DOWN))
    div(BigDecimal(numer), BigDecimal(denom))
      .setScale(Precision).doubleValue
  }
  private def gcd(a: BigInt, b: BigInt): BigInt =
    if (b == BigInt(0)) a else gcd(b, a % b)
  override def hashCode: Int =
    (numer, denom).hashCode
  override def equals(other: Any): Boolean =
    other match {
      case that: BigRational =>
        this.numer == that.numer && this.denom == that.denom ||
        this.numer == 0 && that.numer == 0
      case _ => false
    }
}
object BigRational {
  def unapply(b: BigRational): Some[(BigInt,BigInt)] = Some((b.numer, b.denom))
  implicit def int2BigRational(i: Int): BigRational = new BigRational(i)
}
