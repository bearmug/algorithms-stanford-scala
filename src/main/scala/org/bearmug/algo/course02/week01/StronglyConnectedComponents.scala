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
  final def dfs(stack: Vector[Int], vertices: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack.headOption match {
    case None => (vertices, acc)
    case Some(v) => dMap(v).filter(vertices.contains) match {
      case Nil => dfs(stack.tail, vertices - v, v :: acc)
      case list => dfs(list.toVector ++ stack, vertices - v, acc)
    }
  }

  @tailrec
  final def dfsR(stack: Vector[Int], vertices: Set[Int], acc: List[Int]): (Set[Int], List[Int]) = stack.headOption match {
    case None => (vertices, acc)
    case Some(v) => rMap(v).filter(vertices.contains) match {
      case Nil => dfsR(stack.tail, vertices - v, v :: acc)
      case list => dfsR(list.toVector ++ stack, vertices - v, acc)
    }
  }

  @tailrec
  final def calcFinishOrder(nodes: List[Int], vertices: Set[Int], acc: List[Int]): List[Int] = nodes.headOption match {
    case None => acc
    case Some(vertex) => if (vertices.contains(vertex)) {
      dfs(Vector(vertex), vertices - vertex, List.empty) match {
        case (v, dfsRes) => calcFinishOrder(nodes.tail, v, dfsRes ::: acc)
      }
    } else {
      calcFinishOrder(nodes.tail, vertices - vertex, acc)
    }
  }

  @tailrec
  final def calcScc(nodes: List[Int], vertices: Set[Int], acc: List[Int]): List[Int] = nodes.headOption match {
    case None => acc
    case Some(vertex) => if (vertices.contains(vertex)) {
      dfsR(Vector(vertex), vertices, List.empty) match {
        case (v, dfsRes) => calcScc(nodes.tail, v, dfsRes.length :: acc)
      }
    } else {
      calcScc(nodes.tail, vertices - vertex, acc)
    }
  }

  def calc(): List[Int] = {
    // calc vertices finishing order, put them to sorted structure
    val fOrder = calcFinishOrder(dMap.keySet.toList, dMap.keySet ++ rMap.keySet, List.empty)

    // walk through reversed graph counting SCC size, put them to sorted structure
    calcScc(fOrder, dMap.keySet ++ rMap.keySet, List.empty).sorted(Ordering.Int.reverse)
  }
}

object SCC {
  def apply(l: List[(Int, Int)]): StronglyConnectedComponents = new StronglyConnectedComponents(l)
}
