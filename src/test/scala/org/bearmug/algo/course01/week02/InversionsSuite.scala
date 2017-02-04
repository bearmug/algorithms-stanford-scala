package org.bearmug.algo.course01.week02

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InversionsSuite extends FunSuite {

  test("inversionsFast works for empty input") {
    assert(Inversions(List[Int]()).inversionsFast() == 0)
  }

  test("inversionsFast works for single element") {
    assert(Inversions(List(1)).inversionsFast() == 0)
  }

  test("inversionsFast works for non-inversed sequence") {
    assert(Inversions(List(1, 2, 3, 4, 5, 6)).inversionsFast() == 0)
  }

  test("inversionsFast works for two inversed elements") {
    assert(Inversions(List(2, 1)).inversionsFast() == 1)
  }

  test("inversionsFast failed for of three two inversed elements") {
    assert(Inversions(List(3, 2, 1)).inversionsFast() != 3)
  }
}
