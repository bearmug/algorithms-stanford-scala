package org.bearmug.algo.course02.week03

import scala.collection.immutable.{SortedSet, TreeSet}

/**
  * Created by pavel on 3/12/17.
  */
class Median private(left: SortedSet[Int], right: SortedSet[Int]) {

  type H = SortedSet[Int]

  def +(e: Int): Median = {

    def balance(l: H, r: H): Median = (l.size, r.size) match {
      case (lS, rS) if lS > rS => new Median(l.tail, r + l.head)
      case (lS, rS) if lS < rS => new Median(l + r.head, r.tail)
      case (_, _) => new Median(l, r)
    }

    // use currying for explicit branching
    def add(e: Int)(f: (H, H) => Median): Median = (left.headOption, right.headOption) match {
      case (None, _) => f(left + e, right)
      case (_, None) => f(left, right + e)
      case (lH, _) if e <= lH.get => f(left + e, right)
      case (_, _) => f(left, right + e)
    }

    add(e)(balance)
  }
}

object Median {
  def apply(): Median = new Median(TreeSet.empty(Ordering.Int), TreeSet.empty(Ordering.Int.reverse))
}
