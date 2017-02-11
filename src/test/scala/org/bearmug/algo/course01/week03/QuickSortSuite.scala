package org.bearmug.algo.course01.week03

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class QuickSortSuite extends FunSuite {
  test("sortFirst works for empty input") {
    assert(QuickSort(Vector[Int]()).sort((s: QuickSort#Stripe) => s._2) == Vector())
  }

  test("sortFirst works for one element input") {
    assert(QuickSort(Vector[Int](1)).sort((s: QuickSort#Stripe) => s._2) == Vector(1))
  }
}
