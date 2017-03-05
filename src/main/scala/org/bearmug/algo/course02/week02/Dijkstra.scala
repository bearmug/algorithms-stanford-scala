package org.bearmug.algo.course02.week02

import scala.annotation.tailrec


class Dijkstra private(m: Map[Int, List[(Int, Int)]]) {

  type M = Map[Int, List[(Int, Int)]]
  type N = List[(Int, Int)]

  def shortestPaths(source: Int): N = {

    @tailrec
    def shortestPaths(frontier: N, acc: N): N = frontier match {
      case Nil => acc
      case (v, cost) :: tail => {
        val increment = m(v)
          .filter(n => !acc.map(_._1).toSet.contains(n))
          .map(n => (n._1, n._2 + cost))
        shortestPaths((increment ::: tail).sortBy(_._2).reverse, increment ::: acc)
      }
    }

    shortestPaths(m(source).sortBy(_._2).reverse, List.empty)
  }

}

object Dijkstra {
  def apply(map: Map[Int, List[(Int, Int)]]): Dijkstra =
    new Dijkstra(map.withDefaultValue(List.empty))
}
