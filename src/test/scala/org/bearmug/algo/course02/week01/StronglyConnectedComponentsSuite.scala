package org.bearmug.algo.course02.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.util.Random

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

  test("calc works for generated graph") {
    assert(SCC.calc(List((2,1), (2,3), (1,3), (0,1))) == "2,1,1")
  }

  test("lectures scc algorithm is commutative") {
    assert(SCC.isCommutative(List(
      (1, 7), (7, 4), (4, 1), (7, 9),
      (9, 6), (6, 3), (3, 9), (6, 8),
      (8, 2), (2, 5), (5, 8), (4, 3))))
  }

  test("scc for random graph is commutative") {
    for (_ <- 1 to 1000) {
      val data = (1 to 4).map(_ => (Random.nextInt(4), Random.nextInt(4)))
        .filter(t => t._1 != t._2) // filter 1-node loops
        .toSet // clean duplicates
        .toList
      assert(SCC.isCommutative(data), "isCommutative failed for: " + data)
    }
  }
}
