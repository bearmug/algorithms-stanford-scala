package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.collection.immutable.Queue

class StronglyConnectedComponents(l: List[(Int, Int)]) {
  type G = List[(Int, Int)]

  /**
    * Direct adjacency list graph description
    */
  val dMap = l.groupBy(_._1).map(t => t._1 -> t._2.map(_._2))

  /**
    * Reversed adjacency list graph description
    */
  val rMap = l.groupBy(_._2).map(t => t._1 -> t._2.map(_._1))

  /**
    * Available vertices
    */
  val vertices: Set[Int] = l.map(_._1).toSet ++ l.map(_._2).toSet

  @tailrec
  final def dfs(stack: List[Int], visited: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack match {
    case Nil => (visited, acc)
    case v :: sTail => dMap(v).filter(!visited.contains(_)) match {
      case Nil => dfs(sTail, visited + v, v :: acc)
      case list => dfs(list ::: sTail, visited + v, acc)
    }
  }

  @tailrec
  final def calcFinishOrder(v: Set[Int], acc: List[Int]): List[Int] = v.headOption match {
    case None => acc
    case Some(vertex) => {
      dfs(List(vertex), Set(), List()) match {
        case (s, l) => calcFinishOrder(v --  s, l)
      }
    }
  }

  def calc(): List[Int] = {
    List.empty
    // calc vertices finishing order, put them to sorted structure
//    val order = calcFinishOrder(vertices, List.empty)

    // reverse graph

    // walk through reversed graph counting SCC size, put them to sorted structure
  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents =
    new StronglyConnectedComponents(l)
}
