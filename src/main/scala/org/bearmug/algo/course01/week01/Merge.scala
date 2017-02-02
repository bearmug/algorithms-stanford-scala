package org.bearmug.algo.course01.week01

import scala.collection.immutable.Nil

class Merge[T](data: Seq[T]) {

  def sort(ord: Ordering[T]) = {
    def merge(l: List[T], r: List[T]): List[T] = (l, r) match {
      case (Nil, _) => r
      case (_, Nil) => l
      case (lH :: lT, rH :: rT) => if (ord.lteq(lH, rH)) lH :: merge(lT, r) else rH :: merge(l, rT)
    }

    def sort(seq: Seq[T]): List[T] = seq match {
      case Nil => Nil
      case s :: Nil => List(s)
      case _ => seq.splitAt(seq.length / 2) match {
        case (l, r) => merge(sort(l), sort(r))
      }
    }

    sort(data)
  }
}

object Merge {
  def apply[T](data: Seq[T]) = new Merge(data)
}