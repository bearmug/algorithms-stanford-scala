package org.bearmug.algo.course01.week03

import scala.annotation.tailrec

class QuickSort(data: Vector[Int]) {

  /**
    * Vector index to manipulate to
    */
  type Index = Int

  /**
    * Data vector and its
    */
  type Data = Vector[Int]

  /**
    * Data range to process to: left (inclusive) and right (non-inclusive) border
    */
  type Stripe = (Index, Index)

  @tailrec
  private def sort(d: Data, s: List[Stripe], pivotIndex: (Data, Stripe) => Index): Data = {
    def swap(d: Data, s1: Index, s2: Index): Data = (s1, s2) match {
      case (no, swap) if no == swap => d
      case (_, _) => {
        d.updated(s1, d(s2)).updated(s2, d(s1))
      }
    }

    (d, s) match {
      case (dt, Nil) => dt
      case (dt, (l, r) :: tail) if l + 1 >= r => sort(dt, tail, pivotIndex)
      case (dt, (l, r) :: tail) => {
        var dataInWork = swap(dt, l, pivotIndex(dt, (l, r)))
        var nextLess = l + 1
        //- next position for element smaller than pivot
        var nextMore = l + 1 //- next position for element bigger than pivot
        for (i <- l + 1 until r) {
          dataInWork(i) match {
            case more if more > dataInWork(l) => {
              nextMore += 1
            }
            case _ => {
              dataInWork = swap(dataInWork, nextLess, i)
              nextLess += 1
              nextMore += 1
            }
          }
        }
        var head = tail
        if (l < nextLess - 2) {
          head = (l, nextLess - 1) :: head
        }
        if (nextLess < r - 1) {
          head = (nextLess, r) :: head
        }
        sort(swap(dataInWork, l, nextLess - 1), head, pivotIndex)
      }
    }
  }

  private def sort(pivotIndex: (Data, Stripe) => Index): Data =
    sort(data, (0, data.length) :: Nil, pivotIndex)

  def sortFirst(): Vector[Int] = sort((_, r) => r match {
    case (i, _) => i
  })

  def sortLast(): Vector[Int] = sort((_, r) => r match {
    case (_, i) => i - 1
  })

  def sortMedian(): Vector[Int] = sort((d, s) => (d, s) match {
    case (dt, (l, r)) => {
      val medianIndex = (l + r - 1) / 2
      Vector((l, dt(l)), (r - 1, dt(r - 1)), (medianIndex, dt(medianIndex))).sortBy(_._2) match {
        case Vector(_, (idx, _), _) => idx
      }
    }
  })
}

object QuickSort {
  def apply(data: Vector[Int]) = new QuickSort(data)
}
