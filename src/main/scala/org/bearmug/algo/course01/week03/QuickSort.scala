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

  def swap(data: Stripe, s1: Index, s2: Index): Stripe = data match {
    case (v, l, r) => {
      (v.updated(s1, v(s2)).updated(s2, v(s1)), l, r)
    }
  }

  def sort(data: Stripe, pivotIndex: (Stripe) => Index): Vector[Int] = data match {
    case (v, l, r) if l + 1 >= r => v
    case (v, l, r) => {
      var nextLess = l + 1 //- next position for element smaller than pivot
      var nextMore = l + 1 //- next position for element bigger than pivot
      for (i <- l + 1 until r) {
        v(i) match {
          case more if more > v(l) => {
            nextMore += 1
          }
          case lessEq => {
            swap(data, nextLess, i)
            nextLess += 1
            nextMore += 1
          }
        }
      }
      swap(data, l, nextLess - 1)._1
    }
  }

  def sort(pivotIndex: (Stripe) => Index): Vector[Int] = sort((data, 0, data.length), pivotIndex)
}

object QuickSort {
  def apply(data: Vector[Int]) = new QuickSort(data)
}
