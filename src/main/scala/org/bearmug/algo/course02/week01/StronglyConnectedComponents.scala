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

  @tailrec
  final def dfs(stack: Vector[Int], sources: Set[Int], targets: Set[Int], acc: List[Int]):
  (Set[Int], Set[Int], List[Int]) = stack.headOption match {
    case None => (sources, targets, acc)
    case Some(v) => dMap(v).filter(targets.contains) match {
      case Nil => dfs(stack.tail, sources - v, targets - v, v :: acc)
      case list => dfs(list.filter(n => sources.contains(n) || targets.contains(n)).toVector ++ stack, sources - v, targets -- list - v, acc)
    }
  }

  @tailrec
  final def dfsR(stack: Vector[Int], sources: Set[Int], targets: Set[Int], acc: List[Int]):
  (Set[Int], Set[Int], List[Int]) = stack.headOption match {
    case None => (sources, targets, acc)
    case Some(v) => rMap(v).filter(targets.contains) match {
      case Nil => dfsR(stack.tail, sources - v, targets - v, v :: acc)
      case list => dfsR(list.filter(n => sources.contains(n) || targets.contains(n)).toVector ++ stack, sources - v, targets -- list - v, acc)
    }
  }

  @tailrec
  final def calcFinishOrder(sources: Set[Int], targets: Set[Int], acc: List[Int]): List[Int] = sources.headOption match {
    case None => acc
    case Some(vertex) => dfs(Vector(vertex), sources, targets, List.empty) match {
      case (s, t, dfsRes) => calcFinishOrder(s, t, dfsRes ::: acc)
    }
  }

  @tailrec
  final def calcScc(nodes: List[Int], sources: Set[Int], targets: Set[Int], acc: List[Int]): List[Int] = nodes.headOption match {
    case None => acc
    case Some(vertex) if !sources.contains(vertex) => calcScc(nodes.tail, sources - vertex, targets - vertex, acc)
    case Some(vertex) => dfsR(Vector(vertex), sources, targets, List.empty) match {
      case (s, t, dfsRes) => calcScc(nodes, s, t, dfsRes.length :: acc)
    }
  }

  def calc(): List[Int] = {
    // calc vertices finishing order, put them to sorted structure
    val fOrder = calcFinishOrder(dMap.keySet, rMap.keySet, List.empty)

    // walk through reversed graph counting SCC size, put them to sorted structure
    calcScc(fOrder, fOrder.toSet, dMap.keySet, List.empty).sorted(Ordering.Int.reverse)

  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents =
    new StronglyConnectedComponents(l)
}
