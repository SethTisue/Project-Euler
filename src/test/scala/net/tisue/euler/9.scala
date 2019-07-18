package net.tisue.euler

// There exists exactly one Pythagorean triplet for which a + b + c = 1000.
// Find the product abc.

class Problem9 extends Problem(9, "31875000") {
  def solve = {
    (for{a <- LazyList.from(1)
         b <- 1 to a
         sum = a * a + b * b
         c = math.sqrt(sum).toInt
         if c * c == sum && (a + b + c == 1000)}
     yield(a * b * c)).head
  }
}
