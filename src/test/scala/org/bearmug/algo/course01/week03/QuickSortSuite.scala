package org.bearmug.algo.course01.week03

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class QuickSortSuite extends FunSuite {
  test("sortFirst works for empty input") {
    assert(QuickSort(Vector[Int]()).sortFirst() == Vector())
  }

  test("sortFirst works for one element input") {
    assert(QuickSort(Vector[Int](1)).sortFirst() == Vector(1))
  }

  test("sortFirst works for two element input") {
    assert(QuickSort(Vector[Int](1, 1)).sortFirst() == Vector(1, 1))
    assert(QuickSort(Vector[Int](1, 2)).sortFirst() == Vector(1, 2))
    assert(QuickSort(Vector[Int](2, 1)).sortFirst() == Vector(1, 2))
  }

  test("sortFirst works for three element input") {
    assert(QuickSort(Vector[Int](1, 1, 1)).sortFirst() == Vector(1, 1, 1))
    assert(QuickSort(Vector[Int](1, 2, 3)).sortFirst() == Vector(1, 2, 3))
    assert(QuickSort(Vector[Int](3, 2, 1)).sortFirst() == Vector(1, 2, 3))
  }

  test("sortLast works for 4 element input") {
    assert(QuickSort(Vector[Int](2, 0, 3, 1)).sortLast() == Vector(0, 1, 2, 3))
    assert(QuickSort(Vector[Int](3, 1, 2, 0)).sortLast() == Vector(0, 1, 2, 3))
    assert(QuickSort(Vector[Int](3, 0, 1, 2)).sortLast() == Vector(0, 1, 2, 3))
  }

  test("sortLast works for ten elements") {
    assert(QuickSort(Vector[Int](9, 8, 7, 6, 5, 4, 3, 2, 1, 0)).sortLast() ==
      Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    assert(QuickSort(Vector[Int](4, 0, 1, 6, 5, 9, 3, 2, 7, 8)).sortLast() ==
      Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
  }
}
