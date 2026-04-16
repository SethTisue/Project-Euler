package net.tisue.euler

// Using a 6K text file containing a network with forty vertices, and given in matrix form, find the
// maximum saving which can be achieved by removing redundant edges whilst ensuring that the network
// remains connected.

// en.wikipedia.org/wiki/Minimum_spanning_tree
// en.wikipedia.org/wiki/Reverse-delete_algorithm

// I chose to optimize for simple (and pure-functional) code here and be relatively careless about
// efficiency.  The reverse-delete algorithm is the easiest to understand of the
// minimum-spanning-tree algorithms, I think.  I did include one optimization, using "throw" for
// early exit from pathExists.  The whole thing runs in about 1 second.

class Problem107 extends Problem(107, "259679"):
  type Network[T] = Map[(T, T), Int]
  val input: Network[Int] =
    val entries =
      for
        case (line, v1) <- io.Source.fromResource("107.txt").getLines().zipWithIndex
        case (weight, v2) <- line.split(",").iterator.zipWithIndex
        if weight != "-" && v1 < v2
      yield (v1, v2) -> weight.toInt
    entries.toMap
  def pathExists[T](edge: (T, T), net: Network[T]) =
    object Found extends Throwable
    def traverse(v: T, visited: Set[T] = Set()): Set[T] =
      if v == edge._2 then
        throw Found
      else if visited(v) then
        visited
      else
        net.keys.foldLeft(visited + v): (visited, edge) =>
          edge match
            case (`v`, other) => traverse(other, visited)
            case (other, `v`) => traverse(other, visited)
            case _            => visited
    try
      traverse(edge._1)
      false
    catch case Found => true
  def minimize[T](net: Network[T]) =
    val sortedEdges = net.toSeq.sortBy(_._2).reverse.map(_._1)
    sortedEdges.foldLeft(net): (net, edge) =>
      val erased = net - edge
      if pathExists(edge, erased)
      then erased
      else net
  def weight[T](net: Network[T]) =
    net.values.sum
  def solve =
    weight(input) - weight(minimize(input))
