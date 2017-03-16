package org.bearmug.algo.course02.week04

class TwoSum private(initialSet: Set[Long]) {

  /**
    * Finds number of 2 distinct numbers which give sum equals to goal
    * @param goal target sum
    * @return number of distinct numbers to give target sum
    */
  def calcFor(goal: Int): Int = {
    def calc(set: Set[Long], detected: Set[Long]): Int = set.headOption match {
      case None => detected.size
      case Some(n) if n > goal / 2 => calc(set.tail, detected)
      case Some(n) if detected.contains(n) || !initialSet.contains(goal - n) => calc(set.tail, detected)
      case Some(n) => calc(set - n, detected + n)
    }

    calc(initialSet, Set.empty)
  }

  def satisfyFor(goal: Long): Boolean = {
    initialSet
      .filter(_ <= goal / 2)
      .find(l => l != goal - l && initialSet.contains(goal - l)) match {
      case None => false
      case Some(_) => true
    }
  }
}

object TwoSum {
  def apply(initialSet: Set[Long]): TwoSum = new TwoSum(initialSet)
}
