package org.bearmug.algo.course02.week02

import scala.annotation.tailrec


class Dijkstra private(m: Map[Int, List[(Int, Int)]]) {

  type N = List[(Int, Int)]
  type M = Map[Int, N]

  def shortestPaths(source: Int): N = {

    @tailrec
    def shortestPaths(frontier: N, visited: Set[Int], acc: N): N = frontier match {
      case Nil => acc
      case (v, cost) :: tail => {
        if (visited.contains(v)) {
          shortestPaths(tail, visited, acc)
        } else {
          val increment = m(v).map(n => (n._1, n._2 + cost))
          shortestPaths(
            (increment ::: tail).sortBy(_._2).reverse,
            visited + v,
            ((v, cost) :: acc).sortBy(_._2))
        }
      }
    }

    shortestPaths(m(source), Set(source), List.empty)
  }
}

object Dijkstra {
  def apply(map: Map[Int, List[(Int, Int)]]): Dijkstra =
    new Dijkstra(map.withDefaultValue(List.empty))
}
