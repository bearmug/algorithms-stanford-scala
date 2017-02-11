package org.bearmug.algo.course01.week03

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
        var nextLess = l + 1
        //- next position for element smaller than pivot
        var nextMore = l + 1
        //- next position for element bigger than pivot
        var dataInWork = v
        for (i <- l + 1 until r) {
          v(i) match {
            case more if more > v(l) => {
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
}

object QuickSort {
  def apply(data: Vector[Int]) = new QuickSort(data)
}
