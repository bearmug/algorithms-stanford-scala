package org.bearmug.algo.course02.week04

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwoSumSuite extends FunSuite {
  test("calc works for empty data") {
    assert(TwoSum(List.empty).calc == 0)
  }
}
