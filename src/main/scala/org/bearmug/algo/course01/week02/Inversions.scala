package org.bearmug.algo.course01.week02

import scala.annotation.tailrec
import scala.collection.immutable.Nil
import scala.collection.parallel.ParSeq

class Inversions(data: List[Int]) {

  type Inv = (List[Int], Int)

  @tailrec
  final def merge(l: List[Int], r: List[Int], acc: Int, head: List[Int]): Inv = (l, r) match {
    case (Nil, _) => (head.reverse ::: r, acc)
    case (_, Nil) => (head.reverse ::: l, acc)
    case ((lH :: lT), (rH :: rT)) =>
      if (lH <= rH) {
        merge(lT, r, acc, lH :: head)
      } else {
        merge(l, rT, acc + l.length, rH :: head)
      }
  }

  def inversions(seq: Seq[Int], t: Int): Inv = seq match {
    case Nil => (Nil, 0)
    case e :: Nil => (List(e), 0)
    case _ => seq.splitAt(seq.length / 2) match {
      case (l, r) => {
        val data = if (t <= 0) Seq(l, r) else ParSeq(l, r)
        data.map(inversions(_, t - 1)).seq match {
          case Seq((dataL, accL), (dataR, accR)) =>
            merge(dataL, dataR, accL + accR, Nil)
        }
      }
    }
  }

  def inversionsSimple(): Int = inversions(data, 0) match {
    case (_, inv) => inv
  }

  def inversionsPar(parThreshold: Int): Int =
    inversions(data, parThreshold) match {
      case (_, inv) => inv
    }

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
