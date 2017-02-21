package org.bearmug.algo.course01.week04

import scala.annotation.tailrec
import scala.io.Source
import scala.util.Random



class Karger(m: Map[Int, List[Int]]) {

  def minCut(): Int = {
    def merge(g: Map[Int, List[Int]], i1: Int, i2: Int): Map[Int, List[Int]] = {
      val res = g.updated(i2, g(i1).filter(i2 !=) ::: g(i2).filter(i1 !=)) - i1
      res.map(t => (t._1, t._2.map(v => if (v == i1) i2 else v)))
    }

    @tailrec
    def calcCut(g: Map[Int, List[Int]], keys: Vector[Int]):Int = keys match {
      case Vector() => Int.MaxValue
      case Vector(_) => Int.MaxValue
      case Vector(i1, i2) => (g(i1).count(i2 ==) + g(i2).count(i1 ==)) / 2
      case _ => {
        val i1 = keys(Random.nextInt(keys.size))
        val i2 = g(i1)(Random.nextInt(g(i1).length))
        calcCut(merge(g, i1, i2), keys.filter(i1 !=))
      }
    }

    m match {
      case m if m.isEmpty => Int.MaxValue
      case _ => (0 until m.size).map { _ => calcCut(m, m.keys.toVector) } min
    }
  }
}

object Karger {
  def apply(m: Map[Int, List[Int]]): Karger = new Karger(m)

  def apply(fileName: String): Karger =
    apply(Source.fromFile(fileName).getLines().map{ _.split("\t").map(_.toInt).toList match {
      case n :: rest => n -> rest
    }}.toMap)
}

