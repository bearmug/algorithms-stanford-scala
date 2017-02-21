package org.bearmug.algo.course01.week04

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class KargerSuite extends FunSuite {
  test("minCut works for empty input") {
    assert(Karger(List[(Int, List[Int])]()).minCut() == Int.MaxValue)
  }
}
