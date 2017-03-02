package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class StronglyConnectedComponents(edges: List[(Int, Int)]) {
  type G = List[(Int, Int)]

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
    def calc(m: Map[Int, List[Int]], nodes: List[Int], vertices: Set[Int], acc: List[Int])(f: (List[Int], List[Int]) => List[Int]): List[Int] = {

      @tailrec
      def dfs(stack: Vector[Int], vertices: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack.headOption match {
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

    // calc vertices finishing order, put them to sorted structure
    val fOrder = calc(dMap, dMap.keySet.toList, dMap.keySet ++ rMap.keySet, List.empty) {
      _ ::: _
    }

    // walk through reversed graph counting SCC size, put them to sorted structure
    calc(rMap, fOrder, dMap.keySet ++ rMap.keySet, List.empty) {
      _.length :: _
    } sorted Ordering.Int.reverse
  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents = new StronglyConnectedComponents(l)
}
