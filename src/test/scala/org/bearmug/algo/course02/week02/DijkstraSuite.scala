package org.bearmug.algo.course02.week02

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class DijkstraSuite extends FunSuite {

  test("shortestPaths for empty graph return empty list") {
    assert(Dijkstra(Map.empty).shortestPaths(1) == List.empty)
  }

  test("shortestPaths for one node return empty list") {
    assert(Dijkstra(Map(1 -> List.empty)).shortestPaths(1) == List.empty)
  }
}
