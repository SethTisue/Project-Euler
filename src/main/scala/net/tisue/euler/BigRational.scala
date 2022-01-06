package net.tisue.euler

/// from Programming in Scala book, but converted to use BigInt

import annotation.alpha

class BigRational(n: BigInt, d: BigInt):
  require(d != 0)
  private val g = gcd(n.abs, d.abs)
  private val Precision = 17
  val numer = n / g
  val denom = d / g
  def this(n: BigInt) = this(n, 1)
  @alpha("plus")
  def +(that: BigRational): BigRational =
    BigRational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )
  @alpha("plus")
  def +(i: BigInt): BigRational =
    BigRational(numer + i * denom, denom)
  @alpha("minus")
  def -(that: BigRational): BigRational =
    BigRational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )
  @alpha("minus")
  def -(i: BigInt): BigRational =
    BigRational(numer - i * denom, denom)
  @alpha("times")
  def *(that: BigRational): BigRational =
    BigRational(numer * that.numer, denom * that.denom)
  @alpha("times")
  def *(i: BigInt): BigRational =
    BigRational(numer * i, denom)
  @alpha("divide")
  def /(that: BigRational): BigRational =
    BigRational(numer * that.denom, denom * that.numer)
  @alpha("divide")
  def /(i: BigInt): BigRational =
    BigRational(numer, denom * i)
  def reciprocal: BigRational =
    BigRational(denom, numer)
  override def toString: String =
    s"$numer/$denom"
  def toDouble: Double =
    def div(d1: BigDecimal, d2: BigDecimal) =
      // drop down to java.math.BigDecimal
      BigDecimal(d1.bigDecimal.divide(d2.bigDecimal, Precision, java.math.RoundingMode.DOWN))
    div(BigDecimal(numer), BigDecimal(denom))
      .setScale(Precision).doubleValue
  private def gcd(a: BigInt, b: BigInt): BigInt =
    if b == BigInt(0) then a else gcd(b, a % b)
  override def hashCode: Int =
    (numer, denom).hashCode
  override def equals(other: Any): Boolean =
    // sigh: https://github.com/lampepfl/dotty/issues/10855
    other.asInstanceOf[Matchable] match
      case that: BigRational =>
        this.numer == that.numer && this.denom == that.denom ||
        this.numer == 0 && that.numer == 0
      case _ => false

object BigRational:
  def unapply(b: BigRational): Some[(BigInt,BigInt)] =
    Some((b.numer, b.denom))
