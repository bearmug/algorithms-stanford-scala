package org.bearmug.algo.course02.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class StronglyConnectedComponentsSuite extends FunSuite {

  test("calc works for empty input") {
    assert(SCC.calc(List.empty) == "")
  }

  test("calc works for single edge input") {
    assert(SCC.calc(List((1, 2))) == "1,1")
  }

  test("calc works for two acyclic edge input") {
    assert(SCC.calc(List((1, 2), (2, 3))) == "1,1,1")
  }

  test("calc works for three edges with cycle") {
    assert(SCC.calc(List((1, 2), (2, 3), (3, 1))) == "3")
  }

  test("calc works for three edges with 2-edges cycle") {
    assert(SCC.calc(List((1, 2), (2, 3), (3, 2))) == "2,1")
    assert(SCC.calc(List((1, 2), (2, 1), (2, 3))) == "2,1")
  }

  test("calc works for graph from lectures") {
    assert(SCC.calc(List(
      (1, 7), (7, 4), (4, 1), (7, 9),
      (9, 6), (6, 3), (3, 9), (6, 8),
      (8, 2), (2, 5), (5, 8), (4, 3))) == "3,3,3")
  }
}
