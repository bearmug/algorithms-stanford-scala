package org.bearmug.algo.course02.week03

import scala.collection.immutable.{SortedSet, TreeSet}

/**
  * Created by pavel on 3/12/17.
  */
class Median private(lHeap: SortedSet[Int], rHeap: SortedSet[Int]) {

  type H = SortedSet[Int]

  def +(e: Int): Median = {

    def balance(l: H, r: H): (H, H) = (l.size, r.size) match {
      case (lS, rS) if lS < rS => (l + r.head, r.tail)
      case (lS, rS) if lS > rS + 1 => (l.tail, r + l.head)
      case (_, _) => (l, r)
    }

    // use currying for explicit branching
    def add(e: Int)(f: (H, H) => (H, H)): (H, H) =
      (lHeap.headOption, rHeap.headOption) match {
      case (None, _) => f(lHeap + e, rHeap)
      case (Some(lHead), _) if e <= lHead => f(lHeap + e, rHeap)
      case (_, _) => f(lHeap, rHeap + e)
    }

    Median(add(e)(balance))
  }

  def calc: Int = lHeap.headOption match {
    case Some(m) => m
    case _ => Int.MaxValue
  }
}

object Median {
  def apply(): Median = new Median(TreeSet.empty(Ordering.Int.reverse), TreeSet.empty(Ordering.Int))
  def apply(l: List[Int]): Median = l.foldLeft(Median())((m, i) => m + i)
  private def apply(lr: (SortedSet[Int], SortedSet[Int])) = new Median(lr._1, lr._2)
  def medianSum(l: List[Int]): Int = l.foldLeft((0, Median()))((t, i) => {
    val m = t._2 + i
    (t._1 + m.calc, m)
  })._1
}
