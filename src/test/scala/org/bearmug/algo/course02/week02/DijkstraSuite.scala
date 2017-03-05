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

  test("shortestPaths return empty list with source which has no outgoing edge") {
    assert(Dijkstra(Map(1 -> List((2, 1)))).shortestPaths(2) == List.empty)
    assert(Dijkstra(Map(1 -> List((2, 1)))).shortestPaths(3) == List.empty)
  }

  test("shortestPaths works for single arc graph") {
    assert(Dijkstra(Map(1 -> List((2, 1)))).shortestPaths(1) == List((2, 1)))
  }

  test("shortestPaths works for two vertices cyclic graph") {
    assert(Dijkstra(Map(1 -> List((2, 1)), 2 -> List((1, 1)))).shortestPaths(1) == List((2, 1)))
  }
}
