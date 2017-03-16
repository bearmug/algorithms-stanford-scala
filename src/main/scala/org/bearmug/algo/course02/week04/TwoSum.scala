package org.bearmug.algo.course02.week04

class TwoSum private(s: Set[Int]) {
  def calcFor(goal: Int): Int = {

    def calc(set: Set[Int], detected: Set[Int]): Int = set.headOption match {
      case None => detected.size
      case Some(n) => if (detected.contains(n min (goal - n))) {
        calc(set.tail, detected)
      } else {
        calc(set.tail, detected + (n min (goal - n)))
      }
    }

    calc(s, Set.empty)
  }
}

object TwoSum {
  def apply(s: Set[Int]): TwoSum = new TwoSum(s)
}
