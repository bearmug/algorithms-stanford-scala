package org.bearmug.algo.course01.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MergeSuite extends FunSuite {
  test("sort works for empty input") {
    assert(Merge(Nil).sort(Ordering.Int) == Nil)
  }

  test("sort works for single element input") {
    assert(Merge(List(1)).sort(Ordering.Int) == List(1))
    assert(Merge(List(-23)).sort(Ordering.Int) == List(-23))
    assert(Merge(List(Int.MaxValue)).sort(Ordering.Int) == List(Int.MaxValue))
  }

  test("sort works for 2^n element input") {
    assert(Merge(List(2, 1)).sort(Ordering.Int) == List(1, 2))
    assert(Merge(List(1, 4, 6, 3)).sort(Ordering.Int) == List(1, 3, 4, 6))
    assert(Merge(List(4, 6, 1, 8, 45, 2, 5, 6)).sort(Ordering.Int) == List(1, 2, 4, 5, 6, 6, 8, 45))
    assert(Merge(List(4, 6, 1, 34, -1, 5555, 65, 8, 45, 2, 5, 6, 44, 777, 888, 99)).sort(Ordering.Int) ==
      List(-1, 1, 2, 4, 5, 6, 6, 8, 34, 44, 45, 65, 99, 777, 888, 5555))
  }

  test("sort works for even element input") {
    assert(Merge(List(2, 1, 554, -1, 33, 3)).sort(Ordering.Int) == List(-1, 1, 2, 3, 33, 554))
    assert(Merge(List(4, 6, 1, 8, 45, 2, 5, 6, 45, 5)).sort(Ordering.Int) ==
      List(1, 2, 4, 5, 5, 6, 6, 8, 45, 45))
    assert(Merge(List(4, 6, 1, 34, -1, 5555, 65, 8, 45, 2, 5, 6, 44, 777)).sort(Ordering.Int) ==
      List(-1, 1, 2, 4, 5, 6, 6, 8, 34, 44, 45, 65, 777, 5555))
  }

  test("sort works for odd element input") {
    assert(Merge(List(-123, 2, 1, 554, -1, 33, 3)).sort(Ordering.Int) == List(-123, -1, 1, 2, 3, 33, 554))
    assert(Merge(List(4, 6, 1, 8, 45, 2, 5, 6, 45, 5, -1000)).sort(Ordering.Int) ==
      List(-1000, 1, 2, 4, 5, 5, 6, 6, 8, 45, 45))
    assert(Merge(List(4, 6, 1, 34, -1, 5555, 65, 8, 45, 2, 5, 6, 44, 777, 3)).sort(Ordering.Int) ==
      List(-1, 1, 2, 3, 4, 5, 6, 6, 8, 34, 44, 45, 65, 777, 5555))
  }

  test("sort with reverse order achievable") {
    assert(Merge(List(2, 1, 554, -1, 33, 3)).sort(Ordering.Int.reverse) == List(554, 33, 3, 2, 1, -1))
  }

  test("sort works for strings") {
    assert(Merge(List("asq", "a", "23, ", "yTR")).sort(Ordering.String) ==
      List("23, ", "a", "asq", "yTR"))
  }

  test("sort works for characters") {
    assert(Merge(List('e', 'q', '1', '%')).sort(Ordering.Char) ==
      List('%', '1', 'e', 'q'))
  }
}
