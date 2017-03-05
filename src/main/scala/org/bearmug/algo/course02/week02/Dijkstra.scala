package org.bearmug.algo.course02.week02


class Dijkstra private(map: Map[Int, List[(Int, Int)]]) {

  type M = Map[Int, List[(Int, Int)]]
  type N = List[(Int, Int)]

  def shortestPaths(source: Int): N = {

    def shortestPaths(frontier: N, acc: N): N = frontier match {
      case List() => acc
      case _ => ???
    }

    shortestPaths(map(source), List.empty)
  }

}

object Dijkstra {
  def apply(map: Map[Int, List[(Int, Int)]]): Dijkstra =
    new Dijkstra(map.withDefaultValue(List.empty))
}
