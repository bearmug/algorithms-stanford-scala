package org.bearmug.algo.course02.week01

import scala.annotation.tailrec
import scala.math.Ordering

class SCC private(input: List[Int], allNodes: Set[Int]) {
  type G = List[Int]

  @tailrec
  private def calc(m: Map[Int, G], in: G, nodes: Set[Int], acc: G)(f: (G, G) => G): SCC = {

    @tailrec
    def dfs(dfsIn: Vector[Int], dfsNodes: Set[Int], dfsAcc: G, dfsAccSet: Set[Int]): (Set[Int], G) = dfsIn.headOption match {
      case None => (dfsNodes, dfsAcc)
      case Some(v) => m(v).filter(dfsNodes.contains) match {
        case Nil => if (dfsAccSet.contains(v)) {
          dfs(dfsIn.tail, dfsNodes, dfsAcc, dfsAccSet)
        } else {
          dfs(dfsIn.tail, dfsNodes - v, v :: dfsAcc, dfsAccSet + v)
        }
        case list => dfs(list.toVector ++ dfsIn, dfsNodes - v, dfsAcc, dfsAccSet)
      }
    }

    in.headOption match {
      case None => new SCC(acc, allNodes)
      case Some(vertex) => if (nodes.contains(vertex)) {
        dfs(Vector(vertex), nodes, List.empty, Set.empty) match {
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
  private def apply(l: SCC#G, allNodes: Set[Int]): SCC = new SCC(l, allNodes)

  private def maps(l: List[(Int, Int)]): (Map[Int, SCC#G], Map[Int, SCC#G]) = (
    l.groupBy(_._1).map(t => t._1 -> t._2.map(_._2)).withDefaultValue(Nil),
    l.groupBy(_._2).map(t => t._1 -> t._2.map(_._1)).withDefaultValue(Nil))

  private def calcScc(maps: (Map[Int, SCC#G], Map[Int, SCC#G])): SCC = maps match {
    case (directMap, reversedMap) =>
      SCC(directMap.keySet.toList, directMap.keySet ++ reversedMap.keySet)
        .dfsFor(directMap) {
          _ ::: _
        }.dfsFor(reversedMap) {
          _.length :: _
        }
  }

  def calc(l: List[(Int, Int)]): String = calcScc { maps(l) } result { _ mkString "," }
  def isCommutative(l: List[(Int, Int)]): Boolean = calc(l) == calc(l.map(_.swap))
}
