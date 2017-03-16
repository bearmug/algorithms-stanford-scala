package org.bearmug.algo.course02.week03

import scala.annotation.tailrec
import scala.collection.immutable.{SortedSet, TreeSet}

trait Median {
  def +(e: Int): Median
  def ++(l: List[Int]): Median
  protected def findSet: SortedSet[Int]
  final def calc: Int = findSet.headOption match {
    case Some(m) => m
    case _ => Int.MaxValue
  }
}

object MFactory {

  class MedianDualHeap (lHeap: SortedSet[Int], rHeap: SortedSet[Int]) extends Median {

    type H = SortedSet[Int]

    @tailrec
    private def balance(l: H, r: H): (H, H) = (l.size, r.size) match {
      case (lS, rS) if lS < rS => balance(l + r.head, r.tail)
      case (lS, rS) if lS > rS + 1 => balance(l.tail, r + l.head)
      case (_, _) => (l, r)
    }

    @tailrec
    private def add(list: List[Int], l: H, r: H): (H, H) = list match {
      case Nil => balance(l, r)
      case head :: tail => (l.headOption, r.headOption) match {
        case (None, _) => add(tail, l + head, r)
        case (Some(lHead), _) if head <= lHead => add(tail, l + head, r)
        case (_, _) => add(tail, l, r + head)
      }
    }
    override def ++(l: List[Int]): Median = dualHeap(add(l, lHeap, rHeap))
    override def +(e: Int): Median = this ++ (e :: Nil)
    override protected def findSet: SortedSet[Int] = lHeap
  }

  class MedianHeap (h: SortedSet[Int]) extends Median {
    override def +(e: Int): Median = singleHeap(h + e)
    override def ++(l: List[Int]): Median = singleHeap(h ++ l)
    override protected def findSet: SortedSet[Int] = h.drop((h.size - 1)/2)
  }

  class MedianPlain (s: Set[Int]) extends Median {

    override def +(e: Int): Median = plain(s + e)
    override def ++(l: List[Int]): Median = plain(s ++ l)
    override protected def findSet: SortedSet[Int] = (TreeSet.empty(Ordering.Int) ++ s).drop((s.size - 1)/2)
  }

  private def dualHeap(lr: (SortedSet[Int], SortedSet[Int])): Median = new MedianDualHeap(lr._1, lr._2)
  def dualHeap(): Median = dualHeap(TreeSet.empty(Ordering.Int.reverse), TreeSet.empty(Ordering.Int))

  private def singleHeap(s: SortedSet[Int]): Median = new MedianHeap(s)
  def singleHeap(): Median = singleHeap(TreeSet.empty(Ordering.Int))

  private def plain(s: Set[Int]): Median = new MedianPlain(s)
  def plain(): Median = plain(Set.empty)

  def medianSum(l: List[Int])(f: => Median): Int = l.foldLeft((0, f))((t, i) => {
    val m = t._2 + i
    (t._1 + m.calc, m)
  })._1
}
