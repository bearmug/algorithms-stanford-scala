package org.bearmug.algo.course02.week03

import scala.collection.immutable.{SortedSet, TreeSet}

trait Median {
  def +(e: Int): Median
  protected def findSet: SortedSet[Int]
  def calc: Int = findSet.headOption match {
    case Some(m) => m
    case _ => Int.MaxValue
  }
}

object MFactory {

  class MedianDualHeap (lHeap: SortedSet[Int], rHeap: SortedSet[Int]) extends Median {

    type H = SortedSet[Int]

    override def +(e: Int): Median = {

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

      MFactory.dualHeap(add(e)(balance))
    }

    override protected def findSet: SortedSet[Int] = lHeap
  }

  class MedianHeap (h: SortedSet[Int]) extends Median {

    override def +(e: Int): Median = singleHeap(h + e)

    override protected def findSet: SortedSet[Int] = h.drop((h.size - 1)/2)
  }

  private def dualHeap(lr: (SortedSet[Int], SortedSet[Int])): Median = new MedianDualHeap(lr._1, lr._2)
  def dualHeap(): Median = dualHeap(TreeSet.empty(Ordering.Int.reverse), TreeSet.empty(Ordering.Int))

  private def singleHeap(s: SortedSet[Int]): Median = new MedianHeap(s)
  def singleHeap(): Median = singleHeap(TreeSet.empty(Ordering.Int))

  def forList(l: List[Int])(f: => Median): Median = l.foldLeft(f)((m, i) => m + i)
  def medianSum(l: List[Int])(f: => Median): Int = l.foldLeft((0, f))((t, i) => {
    val m = t._2 + i
    (t._1 + m.calc, m)
  })._1
}
