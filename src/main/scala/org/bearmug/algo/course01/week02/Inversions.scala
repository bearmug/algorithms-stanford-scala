package org.bearmug.algo.course01.week02

import scala.collection.immutable.Nil

class Inversions(data: List[Int]) {

  type Inv = (List[Int], Int)

  def merge(l: List[Int], r: List[Int], acc: Int): Inv = (l, r) match {
    case (Nil, _) => (Nil, acc)
    case (_, Nil) => (Nil, acc)
    case ((lH :: lT), (rH :: rT)) =>
      if (lH <= rH) {
        val res = merge(lT, r, acc)
        (lH :: res._1, res._2)
      } else {
        val res = merge(l, rT, acc + l.length)
        (rH :: res._1, res._2)
      }
  }

  def inversions(seq: Seq[Int]): Inv = seq match {
    case Nil => (Nil, 0)
    case _ :: Nil => (Nil, 0)
    case _ => seq.splitAt(seq.length / 2) match {
      case (l, r) => {
        val invL = inversions(l)
        val invR = inversions(r)
        merge(invL._1, invR._1, invL._2 + invR._2)
      }
    }
  }

  def inversionsSimple(): Int = inversions(data)._2

  def inversionsPar(): Int = ???

  /**
    * Invalid approach since we have may not track full solution tree by this
    */
  def inversionsFast(): Int = data.zipWithIndex.map {
    case (e, index) => (e max index + 1) - (index + 1)
  }.sum
}

object Inversions {
  def apply(data: List[Int]) = new Inversions(data)
}
