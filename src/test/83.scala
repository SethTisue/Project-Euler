package net.tisue.euler

// Find the minimal path sum in matrix.txt (from problems 81 and 82), from the top left to the
// bottom right by moving left, right, up, and down.

// http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm

import collection.immutable.Vector

class Problem83 extends Problem(83, "425185") {
  val matrix = io.Source.fromFile("dat/81.txt")
    .getLines.map(_.trim.split(",").map(_.toInt).to[Vector])
    .to[Vector]
  def neighbors(loc: (Int, Int)) =
    List((-1, 0), (0, -1), (1, 0), (0, 1))
      .map(offsets => (loc._1 + offsets._1, loc._2 + offsets._2))
      .filter(coords => matrix.isDefinedAt(coords._1) &&
                        matrix.isDefinedAt(coords._2))
  val dist = new collection.mutable.HashMap[(Int, Int), Int]
  dist((0, 0)) = matrix(0)(0)
  var queue = matrix.indices.flatMap(i => matrix(i).indices.map((i, _))).toList
  while(!queue.isEmpty) {
    val u = queue.filter(dist.isDefinedAt).minBy(dist)
    queue = queue.filter(_ != u)
    for(v <- neighbors(u)) {
      val alt = dist(u) + matrix(v._1)(v._2)
      if(!dist.isDefinedAt(v) || alt < dist(v))
        dist(v) = alt
    }
  }
  def solve = dist((matrix.indices.last, matrix.head.indices.last))
}
