package org.bearmug.algo.course01.week02

import scala.annotation.tailrec
import scala.collection.parallel.ParSeq

class Inversions(data: Vector[Int]) {

  type Inv = (Vector[Int], Int)

  @tailrec
  final def merge(left: Vector[Int], right: Vector[Int], acc: Int, head: Vector[Int]): Inv = (left, right) match {
    case (Vector(), _) => (head ++ right, acc)
    case (_, Vector()) => (head ++ left, acc)
    case (l, r) if l.head <= r.head => merge(l.tail, r, acc, head :+ l.head)
    case (l, r) => merge(l, r.tail, acc + l.length, head :+ r.head)
  }

  def inversions(v: Vector[Int], t: Int): Inv = v match {
    case Vector() => (Vector(), 0)
    case Vector(e) => (Vector(e), 0)
    case _ => v.splitAt(v.length / 2) match {
      case (l, r) => {
        val data = if (t <= 0) Seq(l, r) else ParSeq(l, r)
        data.map(inversions(_, t - 1)).seq match {
          case Seq((dataL, accL), (dataR, accR)) =>
            merge(dataL, dataR, accL + accR, Vector())
        }
      }
    }
  }

  def inversionsSimple(): Int = inversions(data, 0) match {
    case (_, inv) => inv
  }

  def inversionsPar(parThreshold: Int): Int = inversions(data, parThreshold) match {
    case (_, inv) => inv
  }

  /**
    * Invalid approach since we have may not track full solution tree by this
    */
  def inversionsFast(): Int = data.zipWithIndex.map {
    case (e, index) => (e max index + 1) - (index + 1)
  }.sum

  /**
    * Naive O(n*n) approach
    */
  def inversionsForce(): Int = {
    def invHead(head: Int, tail: Vector[Int]) = tail.fold(0)((acc, e) => if (e < head) acc + 1 else acc)
    @tailrec
    def inv(d: Vector[Int], acc: Int): Int = d match {
      case Vector() => acc
      case _ => inv(d.tail, acc + invHead(d.head, d.tail))
    }

    inv(data, 0)
  }
}

object Inversions {
  def apply(data: Vector[Int]) = new Inversions(data)
}
