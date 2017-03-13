package org.bearmug.algo.course02.week03

import scala.collection.immutable.{SortedSet, TreeSet}

/**
  * Created by pavel on 3/12/17.
  */
class Median private(lHeap: SortedSet[Int], rHeap: SortedSet[Int]) {

  type H = SortedSet[Int]

  def +(e: Int): Median = {

    def balance(l: H, r: H): (H, H) = (l.size, r.size) match {
      case (lS, rS) if lS > rS => (l.tail, r + l.head)
      case (lS, rS) if lS + 1 < rS => (l + r.head, r.tail)
      case (_, _) => (l, r)
    }

    // use currying for explicit branching
    def add(e: Int)(f: (H, H) => (H, H)): (H, H) =
      (lHeap.headOption, rHeap.headOption) match {
      case (_, None) => f(lHeap, rHeap + e)
      case (_, Some(rHead)) if e >= rHead => f(lHeap, rHeap + e)
      case (_, _) => f(lHeap + e, rHeap)
    }

    Median(add(e)(balance))
  }

  def calc: Int = rHeap.headOption match {
    case Some(m) => m
    case _ => Int.MaxValue
  }
}

object Median {
  def apply(): Median = new Median(TreeSet.empty(Ordering.Int.reverse), TreeSet.empty(Ordering.Int))
  private def apply(lr: (SortedSet[Int], SortedSet[Int])) = new Median(lr._1, lr._2)
}
