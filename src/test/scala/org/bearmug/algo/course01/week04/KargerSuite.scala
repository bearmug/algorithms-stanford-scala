package org.bearmug.algo.course01.week04

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class KargerSuite extends FunSuite {
  test("minCut works for empty input") {
    assert(Karger(List[(Int, List[Int])]()).minCut() == Int.MaxValue)
  }

  test("minCut works for two-nodes graph") {
    assert(Karger(List((1, List(2)), (2, List(1)))).minCut() == 1)
  }

  test("minCut works for two-nodes graph with dupe links") {
    assert(Karger(List((1, List(2, 2)), (2, List(1, 1)))).minCut() == 2)
  }
}
