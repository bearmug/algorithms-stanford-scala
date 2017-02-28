package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class StronglyConnectedComponents(l: List[(Int, Int)]) {
  type G = List[(Int, Int)]

  /**
    * Direct adjacency list graph description
    */
  val dMap = l.groupBy(_._1).map(t => t._1 -> t._2.map(_._2)).withDefaultValue(List.empty)

  /**
    * Reversed adjacency list graph description
    */
  val rMap = l.groupBy(_._2).map(t => t._1 -> t._2.map(_._1)).withDefaultValue(List.empty)

  /**
    * Available vertices
    */
  val vertices: Set[Int] = l.map(_._1).toSet ++ l.map(_._2).toSet

  @tailrec
  final def dfs(stack: Vector[Int], visited: Set[Int], acc: List[Int], m: Map[Int, List[Int]]):
  (Set[Int], List[Int], Map[Int, List[Int]]) = stack.headOption match {
    case None => (visited, acc, m)
    case Some(v) => m(v) match {
      case Nil => dfs(stack.tail, visited + v, v :: acc, m)
      case list => dfs(list.toVector ++ stack, visited + v, acc, m - v)
    }
  }

  @tailrec
  final def dfsR(stack: Vector[Int], visited: Set[Int], acc: List[Int], m: Map[Int, List[Int]]):
  (Set[Int], List[Int], Map[Int, List[Int]]) = stack.headOption match {
    case None => (visited, acc, m)
    case Some(v) => m(v).filter(!visited.contains(_)) match {
      case Nil => dfsR(stack.tail, visited + v, v :: acc, m)
      case list => dfsR(list.toVector ++ stack, visited + v, acc, m - v)
    }
  }

  @tailrec
  final def calcFinishOrder(vertices: Set[Int], visited: Set[Int], acc: List[Int], m: Map[Int, List[Int]]): List[Int] = vertices.headOption match {
    case None => acc
    case Some(vertex) => dfs(Vector(vertex), visited, List(), m) match {
      case (s, l, newMap) => calcFinishOrder(vertices -- s, visited ++ s, l ::: acc, newMap)
    }
  }

  @tailrec
  final def calcScc(vertices: List[Int], visited: Set[Int], acc: List[Int], m: Map[Int, List[Int]]): List[Int] = vertices match {
    case Nil => acc
    case v :: vTail => dfsR(Vector(v), visited, List(), m) match {
      case (s, l, newMap) => calcScc(vTail.filter(!s.contains(_)), visited ++ s, l.length :: acc, newMap)
    }
  }

  def calc(): List[Int] = {
    // calc vertices finishing order, put them to sorted structure
    val order = calcFinishOrder(vertices, Set(), List(), dMap)

    // walk through reversed graph counting SCC size, put them to sorted structure
    calcScc(order, Set(), List(), rMap).sorted(Ordering.Int.reverse)

  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents =
    new StronglyConnectedComponents(l)
}
