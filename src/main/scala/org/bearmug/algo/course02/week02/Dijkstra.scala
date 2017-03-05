package org.bearmug.algo.course02.week02

import scala.annotation.tailrec
import scala.collection.immutable.TreeSet


class Dijkstra private(m: Map[Int, List[(Int, Int)]]) {

  type N = List[(Int, Int)]
  type M = Map[Int, N]

  val ord: Ordering[(Int, Int)] = math.Ordering.by(_._2)

  def shortestPaths(source: Int): N = {
    @tailrec
    def shortestPaths(frontier: TreeSet[(Int, Int)], visited: Set[Int], acc: N): N = frontier.headOption match {
      case None => acc.sortBy(_._2)
      case Some((vert, cost)) => if (visited.contains(vert)) {
        shortestPaths(frontier.tail, visited, acc)
      } else {
        shortestPaths(
          frontier.tail ++ m(vert).map { case (v, c) => (v, c + cost) }, visited + vert, (vert, cost) :: acc)
      }
    }

    shortestPaths(TreeSet.empty(ord) ++ m(source), Set(source), List.empty)
  }
}

object Dijkstra {
  def apply(m: Map[Int, List[(Int, Int)]]): Dijkstra = new Dijkstra(m.withDefaultValue(List.empty))
}
