package org.bearmug.algo.course02.week01

class StronglyConnectedComponents(d: Map[Int, Set[Int]], r: Map[Int, Set[Int]]) {
  type G = Map[Int, Set[Int]]

  def calc(): List[Int] = List.empty
}

object SCC {
  def apply(d: Map[Int, Set[Int]], r: Map[Int, Set[Int]]): StronglyConnectedComponents =
    new StronglyConnectedComponents(d, r)
}
