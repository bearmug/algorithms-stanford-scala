package org.bearmug.algo.course01.week03

import scala.math.Ordering

class QuickSort(data: Vector[Int]) {

  /**
    * Vector index to manipulate to
    */
  type Index = Int

  /**
    * Data vector and its left (inclusive) and right (exclusive) border
    */
  type Stripe = (Vector[Int], Index, Index)

  private def sort(data: Stripe, pivotIndex: (Stripe) => Index): Vector[Int] = {
    def swap(data: Stripe): Vector[Int] = data match {
      case (v, s1, s2) if s1 == s2 => v
      case (v, s1, s2) => {
        v.updated(s1, v(s2)).updated(s2, v(s1))
      }
    }

    data match {
      case (v, l, r) if l + 1 >= r => v
      case (v, l, r) => {
        var dataInWork = swap(v, l, pivotIndex(v, l, r))
        var nextLess = l + 1 //- next position for element smaller than pivot
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
        val presorted = swap(dataInWork, l, nextLess - 1)
        val sortedLeft = sort((presorted, l, nextLess - 1), pivotIndex)
        sort((sortedLeft, nextLess, r), pivotIndex)
      }
    }
  }

  private def sort(pivotIndex: (Stripe) => Index): Vector[Int] = sort((data, 0, data.length), pivotIndex)

  def sortFirst(): Vector[Int] = sort((s) => s._2)
  def sortLast(): Vector[Int] = sort((s) => s._3 - 1  )
  def sortMedian(): Vector[Int] = sort((s) => s match {
    case (v, l, r) => {
      val medianIndex = (l + r) / 2
      Vector((l, v(l)), (r - 1, v(r - 1)), (medianIndex, v(medianIndex))).sortBy(_._2) match {
        case Vector(_, (idx, _), _) => idx
      }
    }
  })
}

object QuickSort {
  def apply(data: Vector[Int]) = new QuickSort(data)
}
