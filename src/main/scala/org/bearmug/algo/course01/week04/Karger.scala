package org.bearmug.algo.course01.week04

import scala.annotation.tailrec
import scala.io.Source
import scala.util.Random



class Karger(v: List[(Int, List[Int])]) {

  type Vertex = (Int, List[Int])

  def merge(v1: Vertex, v2: Vertex): Vertex = (v1, v2) match {
    case ((i1, l1),(i2, l2)) => (i2, l1.filter(i2 !=) ::: l2.filter(i1 !=))
  }

  @tailrec
  final def replace(list: List[Vertex], v1: Vertex, v2: Vertex, res: List[Vertex]): List[Vertex] = list match {
    case Nil => res
    case (v, l) :: tail => replace(tail, v1, v2, (v, l.map(i => if (i == v1._1) v2._1 else i)) :: res)
  }

  @tailrec
  final def calcCut(g: List[Vertex]):Int = g match {
    case Nil => Int.MaxValue
    case _ :: Nil => Int.MaxValue
    case (i1, n1) :: (i2, n2) :: Nil => n1.count(i2 ==) + n2.count(i1 ==)
    case v1 :: v2 :: tail => calcCut(merge(v1, v2) :: replace(tail, v1, v2, List()))
  }

  def minCut(): Int = (0 to v.size + 1).map { _ => calcCut(Random.shuffle(v)) } min
}

object Karger {
  def apply(v: List[(Int, List[Int])]): Karger = new Karger(v)

  def apply(fileName: String): Karger =
    apply(Source.fromFile(fileName).getLines().map{ _.split("\t").map(_.toInt).toList match {
      case n :: rest => (n, rest)
    }}.toList)
}

