package org.bearmug.algo.course01.week04

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class KargerSuite extends FunSuite {
  test("minCut works for empty input") {
    assert(Karger(Map[Int, List[Int]]()).minCut() == Int.MaxValue)
  }

  test("minCut works for one-node graph") {
    assert(Karger(Map(1 -> List(2))).minCut() == Int.MaxValue)
  }

  test("minCut works for two-nodes graph") {
    assert(Karger(Map(1 -> List(2), 2 -> List(1))).minCut() == 1)
  }

  test("minCut works for two-nodes graph with dupe links") {
    assert(Karger(Map(1 -> List(2, 2), 2 -> List(1, 1))).minCut() == 2)
  }

  test("minCut works for three-nodes graph") {
    assert(Karger(Map(1 -> List(2, 3), 2 -> List(1, 3), 3 -> List(1, 2))).minCut() == 2)
  }

  test("minCut works for cyclic graph") {
    val l = (0 to 99).map(i => i -> List((i + 1) % 100, (i + 99) % 100)).toMap
    assert(Karger(l).minCut() == 2)
  }

  //time-consuming test
  ignore("minCut works for test file") {
    assert(Karger("/karger.data").minCut() == 17)
  }
}
