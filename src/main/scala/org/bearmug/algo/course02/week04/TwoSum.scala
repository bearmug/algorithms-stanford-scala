package org.bearmug.algo.course02.week04

class TwoSum private(s: Set[Long]) {

  /**
    * Finds number of 2 distinct numbers which give sum equals to goal
    * @param goal target sum
    * @return number of distinct numbers to give target sum
    */
  def calcFor(goal: Int): Int = {
    def calc(set: Set[Long], detected: Set[Long]): Int = set.headOption match {
      case None => detected.size
      case Some(n) => if (set.contains(goal - n) && !detected.contains(n min (goal - n))) {
        calc(set - n - (goal - n), detected + (n min (goal - n)))
      } else {
        calc(set.tail, detected)
      }
    }

    calc(s, Set.empty)
  }

  def satisfyFor(goal: Long): Boolean = {
    s.find(l => l != goal - l && s.contains(goal - l)) match {
      case None => false
      case Some(_) => true
    }
  }
}

object TwoSum {
  def apply(s: Set[Long]): TwoSum = new TwoSum(s)
}
