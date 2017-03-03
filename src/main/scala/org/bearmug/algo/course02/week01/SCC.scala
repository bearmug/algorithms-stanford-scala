package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class SCC(input: List[Int], allNodes: Set[Int]) {
  type G = List[Int]

  @tailrec
  final def calc(m: Map[Int, G], in: G, nodes: Set[Int], acc: G)(f: (G, G) => G): SCC = {

    @tailrec
    def dfs(stack: Vector[Int], vertices: Set[Int], acc: G): (Set[Int], G) = stack.headOption match {
      case None => (vertices, acc)
      case Some(v) => m(v).filter(vertices.contains) match {
        case Nil => dfs(stack.tail, vertices - v, v :: acc)
        case list => dfs(list.toVector ++ stack, vertices - v -- list, acc)
      }
    }

    in.headOption match {
      case None => new SCC(acc, allNodes)
      case Some(vertex) => if (nodes.contains(vertex)) {
        dfs(Vector(vertex), nodes, List.empty) match {
          case (v, dfsRes) => calc(m, in.tail, v, f(dfsRes, acc))(f)
        }
      } else {
        calc(m, in.tail, nodes, acc)(f)
      }
    }
  }

  def dfsFor(m: Map[Int, G])(f: (G, G) => G): SCC = calc(m, input, allNodes, List.empty)(f)

  def result[T](f: (G) => T): T = f(input sorted Ordering.Int.reverse)
}

object SCC {
  private def apply(l: List[Int], allNodes: Set[Int]): SCC = new SCC(l, allNodes)

  def calc(l: List[(Int, Int)]): String = {
    val dMap = l.groupBy(_._1).map(t => t._1 -> t._2.map(_._2)).withDefaultValue(Nil)
    val rMap = l.groupBy(_._2).map(t => t._1 -> t._2.map(_._1)).withDefaultValue(Nil)

    SCC(rMap.keySet.toList, dMap.keySet ++ rMap.keySet)
      .dfsFor(rMap) {
        _ ::: _
      }.dfsFor(dMap) {
        _.length :: _
      }.result {
        _ mkString ","
      }
  }
}
