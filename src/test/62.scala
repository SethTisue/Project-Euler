package net.tisue.euler

// Find the smallest cube for which exactly five permutations of its digits are cube.

class Problem62 extends Problem(62, "127035954683") {
  val cubes = Stream.from(1).map(n => BigInt(n) * n * n)
  def cubesOfLength(len:Int) =
    cubes.dropWhile(_.toString.size < len).takeWhile(_.toString.size == len)
  def solve = {
    // the use of a mutable data structure is completely encapsulated
    // within cubeMap; still, I'd prefer a pure-functional solution
    def cubeMap(len:Int) = {
      val m = new collection.mutable.HashMap[List[Int],Int]
      for{c <- cubesOfLength(len); ds = c.digits.sorted.toList}
        m(ds) = if(m.contains(ds)) m(ds) + 1 else 1
      m.toMap
    }
    val solutions = for{len <- Stream.from(1)
                        m = cubeMap(len)
                        n <- cubesOfLength(len)
                        if m(n.digits.sorted.toList) == 5}
                    yield n
    solutions.head
  }
}
