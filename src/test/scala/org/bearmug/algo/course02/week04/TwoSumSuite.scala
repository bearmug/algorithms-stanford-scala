package org.bearmug.algo.course02.week04

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TwoSumSuite extends FunSuite {
  test("calc works for empty data") {
    assert(TwoSum(Set.empty).calcFor(1) == 0)
  }

  test("calc works for single sum") {
    assert(TwoSum(Set(1, 2)).calcFor(3) == 1)
    assert(TwoSum(Set(5, -2)).calcFor(3) == 1)
  }

  test("calc works for ten numbers") {
    assert(TwoSum(Set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).calcFor(11) == 5)
  }

  test("calc works for 1000 numbers") {
    assert(TwoSum((1L to 1000L).toSet).calcFor(400) == 200)
  }

  test("satisfyFor is false for unavailable sum") {
    assert(!TwoSum((1L to 1000L).toSet).satisfyFor(2001))
  }

  test("satisfyFor does not match to 2 same numbers") {
    assert(!TwoSum(Set(1)).satisfyFor(2))
  }

  test("satisfyFor is true for available sum") {
    assert(TwoSum((1L to 1000L).toSet).satisfyFor(333))
  }
}
