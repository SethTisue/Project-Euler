package net.tisue.euler

object Primes:

  private final val Certainty = 100

  // simple primality test
  def isPrime(n: Int): Boolean =
    BigInt(n).isProbablePrime(Certainty)
  def isPrime(n: Long): Boolean =
    BigInt(n).isProbablePrime(Certainty)
  def isPrime(n: BigInt): Boolean =
    n.isProbablePrime(Certainty)

  // prime sieve; use instead of the test or stream when you know the
  // upper bound of the primes you will need.
  // en.wikipedia.org/wiki/Sieve_of_Eratosthenes
  // this is the genuine sieve algorithm; see www.cs.hmc.edu/~oneill/papers/Sieve-JFP.pdf
  // for a discussion of impostors
  def primesBelow(limit: Int): List[Int] =
    val sieve = Array.fill(limit)(true)
    sieve(1) = false
    // we use while instead of for comprehensions for a little extra speed - ST 7/19/10
    var i = 2
    val stop = math.sqrt(limit).toInt
    while i <= stop do
      if sieve(i) then
        var j = i * 2
        while j < limit do
          sieve(j) = false
          j += i
      i += 1
    (2 until limit).filter(sieve(_)).toList

  // primality test using a sieve that doubles in size if we ask for a number larger than the current
  // sieve.  so unlike primesBelow, this doesn't require committing in advance to a ceiling on the
  // amount of primes you will need.
  var sieve = Array(false, false, true, true)
  private val lock = AnyRef()
  def isSievedPrime(n: Int): Boolean = lock.synchronized:
    while n >= sieve.length do
      val newSieve = Array.fill(sieve.length * 2)(true)
      Array.copy(sieve, 0, newSieve, 0, sieve.length)
      // we use while instead of for comprehensions for a little extra speed - ST 7/19/10
      var i = 2
      val stop = math.sqrt(newSieve.length).toInt
      while i <= stop do
        if newSieve(i) then
          // start at least multiple of i at or above sieve.length
          var j = i * ((sieve.length - 1) / i + 1)
          while j < newSieve.length do
            newSieve(j) = false
            j += i
        i += 1
      sieve = newSieve
    sieve(n)

  // stream of cached sieved primes
  val primes: LazyList[Int] = LazyList.from(2).filter(isSievedPrime)

  // factorization
  def factors(n: Int): LazyList[Int] =
    require(n > 1)
    if isSievedPrime(n) then
      LazyList(n)
    else
      val f = primes.find(n % _ == 0).get
      f #:: factors(n / f)
  // this is only going to work for BigInts that only have small prime factors
  def factors(n: BigInt): LazyList[Int] =
    require(n > 1)
    if isPrime(n) then
      LazyList(n.toInt)
    else
      val f = primes.find(n % _ == 0).get
      f #:: factors(n / f)
  def factorCounts(i: Int): List[Int] =
    def recurse(fs: LazyList[Int]): List[Int] =
      if fs.isEmpty then
        Nil
      else
        val (firstGroup, moreGroups) = fs.span(_ == fs.head)
        firstGroup.size :: recurse(moreGroups)
    recurse(factors(i))
  def factorCounts(i: BigInt): List[Int] =
    def recurse(fs: LazyList[Int]): List[Int] =
      if fs.isEmpty then Nil
      else
        val (firstGroup, moreGroups) = fs.span(_ == fs.head)
        firstGroup.size :: recurse(moreGroups)
    recurse(factors(i))
