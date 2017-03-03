package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class StronglyConnectedComponents (input: List[Int], available: Set[Int]) {
  type G = List[Int]

  @tailrec
  final def calc(m: Map[Int, G], in: G, avl: Set[Int], acc: G)(f: (G, G) => G): StronglyConnectedComponents = {

    @tailrec
    def dfs(stack: Vector[Int], vertices: Set[Int], acc: G): (Set[Int], G) = stack.headOption match {
      case None => (vertices, acc)
      case Some(v) => m(v).filter(vertices.contains) match {
        case Nil => dfs(stack.tail, vertices - v, v :: acc)
        case list => dfs(list.toVector ++ stack, vertices - v -- list, acc)
      }
    }

    in.headOption match {
      case None => new StronglyConnectedComponents(acc, available)
      case Some(vertex) => if (avl.contains(vertex)) {
        dfs(Vector(vertex), avl, List.empty) match {
          case (v, dfsRes) => calc(m, in.tail, v, f(dfsRes, acc))(f)
        }
      } else {
        calc(m, in.tail, avl, acc)(f)
      }
    }
  }

  def dfsFor(m: Map[Int, G])(f: (G, G) => G): StronglyConnectedComponents =
    calc(m, input, available, List.empty)(f)

  def result: String = input sorted Ordering.Int.reverse mkString ","
}

object SCC {
  private def apply(l: List[Int], v: Set[Int]): StronglyConnectedComponents = new StronglyConnectedComponents(l, v)

  def calc(l: List[(Int, Int)]): String = {
    val dMap = l.groupBy(_._1).map(t => t._1 -> t._2.map(_._2)).withDefaultValue(Nil)
    val rMap = l.groupBy(_._2).map(t => t._1 -> t._2.map(_._1)).withDefaultValue(Nil)

    SCC(rMap.keySet.toList, dMap.keySet ++ rMap.keySet)
      .dfsFor(rMap) {
        _ ::: _
      }.dfsFor(dMap) {
        _.length :: _
      } result
  }
}
