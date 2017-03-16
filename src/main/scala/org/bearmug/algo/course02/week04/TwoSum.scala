package org.bearmug.algo.course02.week04

class TwoSum private(initialSet: Set[Long]) {

  /**
    * Finds number of 2 distinct numbers which give sum equals to goal
    *
    * @param goal target sum
    * @return number of distinct numbers to give target sum
    */
  def calcFor(goal: Int): Int = initialSet
    .filter(2 * _ < goal)
    .count(n => initialSet.contains(goal - n))

  def satisfyFor(goal: Long): Boolean = initialSet
    .filter(2 * _ < goal)
    .find(n => initialSet.contains(goal - n)) match {
    case None => false
    case Some(_) => true
  }
}

object TwoSum {
  def apply(initialSet: Set[Long]): TwoSum = new TwoSum(initialSet)
}
