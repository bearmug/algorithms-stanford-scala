package org.bearmug.algo.course02.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StronglyConnectedComponentsSuite extends FunSuite {

  test("calc works for empty input") {
    assert(SCC(List.empty).calc() == List.empty)
  }

//  test("calc works for single edge input") {
//    assert(SCC(List((1, 2))).calc() == List(1, 1))
//  }
}
