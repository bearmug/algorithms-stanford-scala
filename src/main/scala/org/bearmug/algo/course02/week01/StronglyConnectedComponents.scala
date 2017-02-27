package org.bearmug.algo.course02.week01

class StronglyConnectedComponents(l: List[(Int, Int)]) {
  type G = List[(Int, Int)]

  def calc(): List[Int] = List.empty
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents =
    new StronglyConnectedComponents(l)
}
