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
  final def dfs(stack: List[Int], visited: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack match {
    case Nil => (visited, acc)
    case v :: sTail => dMap(v).filter(!visited.contains(_)) match {
      case Nil => dfs(sTail, visited + v, v :: acc)
      case list => dfs(list ::: v :: sTail, visited + v, acc)
    }
  }

  @tailrec
  final def dfsR(stack: List[Int], visited: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack match {
    case Nil => (visited, acc)
    case v :: sTail => rMap(v).filter(!visited.contains(_)) match {
      case Nil => dfsR(sTail, visited + v, v :: acc)
      case list => dfsR(list ::: v :: sTail, visited + v, acc)
    }
  }

  @tailrec
  final def calcFinishOrder(vertices: Set[Int], visited: Set[Int], acc: List[Int]): List[Int] = vertices.headOption match {
    case None => acc
    case Some(vertex) => dfs(List(vertex), visited, List()) match {
      case (s, l) => calcFinishOrder(vertices -- s, visited ++ s, l ::: acc)
    }
  }

  @tailrec
  final def calcScc(vertices: List[Int], visited: Set[Int], acc: List[Int]): List[Int] = vertices match {
    case Nil => acc
    case v :: vTail => dfsR(List(v), visited, List()) match {
      case (s, l) => calcScc(vTail.filter(!s.contains(_)), visited ++ s, l.length :: acc)
    }
  }

  def calc(): List[Int] = {
    // calc vertices finishing order, put them to sorted structure
    val order = calcFinishOrder(vertices, Set(), List())

    // walk through reversed graph counting SCC size, put them to sorted structure
    calcScc(order, Set(), List()).sorted(Ordering.Int.reverse)

  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents =
    new StronglyConnectedComponents(l)
}
