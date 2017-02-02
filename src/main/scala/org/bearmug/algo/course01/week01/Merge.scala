package org.bearmug.algo.course01.week01

import scala.collection.immutable.Nil

class Merge[T](data: Seq[T], ord: Ordering[T]) {

  def merge(l: List[T], r: List[T]): List[T] = (l, r) match {
    case (Nil, _) => r
    case (_, Nil) => l
    case (lH :: lT, rH :: rT) => if (ord.lteq(lH, rH)) lH :: merge(lT, r) else rH :: merge(l, rT)
  }

  def sort(seq: Seq[T], parLevels: Int): List[T] = seq match {
    case Nil => Nil
    case s :: Nil => List(s)
    case _ => seq.splitAt(seq.length / 2) match {
      case (l, r) => merge(sort(l, parLevels - 1), sort(r, parLevels - 1))
    }
  }

  def sort(): Seq[T] = sort(data, 0)

  def sortPar(parLevels: Int): Seq[T] = sort(data, parLevels)
}

object Merge {
  def apply[T](data: Seq[T], ord: Ordering[T]) = new Merge(data, ord)
}