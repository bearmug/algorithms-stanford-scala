package org.bearmug.algo.course01.week01

import scala.collection.immutable.Nil

class Merge[T](nums: Seq[T]) {

  def sort(lessEq: (T, T) => Boolean) = {
    def merge(l: List[T], r: List[T]): List[T] = (l, r) match {
      case (Nil, _) => r
      case (_, Nil) => l
      case (lH :: lT, rH :: rT) => if (lessEq(lH, rH)) lH :: merge(lT, r) else rH :: merge(l, rT)
    }

    def sort(seq: Seq[T]): List[T] = seq match {
      case Nil => Nil
      case s :: Nil => List(s)
      case _ => seq.splitAt(seq.length / 2) match {
        case (l, r) => merge(sort(l), sort(r))
      }
    }

    sort(nums)
  }
}

object Merge {
  def apply(nums: Seq[Int]) = new Merge(nums)
}