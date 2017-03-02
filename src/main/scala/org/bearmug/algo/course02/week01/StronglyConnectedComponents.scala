package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class StronglyConnectedComponents(edges: List[(Int, Int)]) {
  type G = List[Int]

  /**
    * Direct adjacency list graph description
    */
  private val dMap = edges.groupBy(_._1).map(t => t._1 -> t._2.map(_._2)).withDefaultValue(Nil)

  /**
    * Reversed adjacency list graph description
    */
  private val rMap = edges.groupBy(_._2).map(t => t._1 -> t._2.map(_._1)).withDefaultValue(Nil)

  def calc(): List[Int] = {

    @tailrec
    def calc(m: Map[Int, G], nodes: G, vertices: Set[Int], acc: G)(f: (G, G) => G): G = {

      @tailrec
      def dfs(stack: Vector[Int], vertices: Set[Int], acc: G): (Set[Int], G) = stack.headOption match {
        case None => (vertices, acc)
        case Some(v) => m(v).filter(vertices.contains) match {
          case Nil => dfs(stack.tail, vertices - v, v :: acc)
          case list => dfs(list.toVector ++ stack, vertices - v -- list, acc)
        }
      }

      nodes.headOption match {
        case None => acc
        case Some(vertex) => if (vertices.contains(vertex)) {
          dfs(Vector(vertex), vertices, List.empty) match {
            case (v, dfsRes) => calc(m, nodes.tail, v, f(dfsRes, acc))(f)
          }
        } else {
          calc(m, nodes.tail, vertices, acc)(f)
        }
      }
    }

    val vertices = dMap.keySet ++ rMap.keySet
    // calc vertices finishing order, put them to sorted structure
    val fOrder = calc(dMap, dMap.keySet.toList, vertices, List.empty) {
      _ ::: _
    }

    // walk through reversed graph counting SCC size, put them to sorted structure
    calc(rMap, fOrder, vertices, List.empty) {
      _.length :: _
    } sorted Ordering.Int.reverse
  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents = new StronglyConnectedComponents(l)
}
