package org.bearmug.algo.course01.week01

import scala.collection.immutable.Nil

class Merge(nums: Seq[Int]) {

  def sort() = {
    def merge(l: List[Int], r: List[Int]): List[Int] = (l, r) match {
      case (Nil, _) => r
      case (_, Nil) => l
      case (lH :: lT, rH :: rT) => if (lH <= rH) lH :: merge(lT, r) else rH :: merge(l, rT)
    }

    def sort(seq: Seq[Int]): List[Int] = seq match {
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