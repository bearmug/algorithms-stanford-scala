package org.bearmug.algo.course01.week02

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InversionsSuite extends FunSuite {

  test("inversionsFast works for empty input") {
    assert(Inversions(Vector[Int]()).inversionsFast() == 0)
  }

  test("inversionsFast works for single element") {
    assert(Inversions(Vector(1)).inversionsFast() == 0)
  }

  test("inversionsFast works for non-inversed sequence") {
    assert(Inversions(Vector(1, 2, 3, 4, 5, 6)).inversionsFast() == 0)
  }

  test("inversionsFast works for two inversed elements") {
    assert(Inversions(Vector(2, 1)).inversionsFast() == 1)
  }

  test("inversionsFast failed for of three two inversed elements") {
    assert(Inversions(Vector(3, 2, 1)).inversionsFast() != 3)
  }

  test("inversionsSimple works for empty input") {
    assert(Inversions(Vector[Int]()).inversionsSimple() == 0)
  }

  test("inversionsSimple works for single element") {
    assert(Inversions(Vector(1)).inversionsSimple() == 0)
  }

  test("inversionsSimple works for sorted sequence") {
    assert(Inversions(Vector(1, 2, 3, 4, 5, 6)).inversionsSimple() == 0)
  }

  test("inversionsSimple works for two inversed elements") {
    assert(Inversions(Vector(2, 1)).inversionsSimple() == 1)
  }

  test("inversionsSimple works for of three two inversed elements") {
    assert(Inversions(Vector(3, 2, 1)).inversionsSimple() == 3)
  }

  test("inversionsSimple works for 6 inversed elements") {
    assert(Inversions(Vector(6, 5, 4, 3, 2, 1)).inversionsSimple() == (6 / 2) * (6 - 1))
  }

  test("inversionsSimple works for 10 inversed elements") {
    assert(Inversions(Vector(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)).inversionsSimple() == (10 / 2) * (10 - 1))
  }

  test("inversionsPar works for 10 inversed elements") {
    assert(Inversions(Vector(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)).inversionsPar(4) == (10 / 2) * (10 - 1))
  }

  test("inversionsSimple works for 100000 inversed elements") {
    assert(Inversions((1 to 100000 reverse).toVector).inversionsSimple() == (100000 / 2) * (100000 - 1))
  }

  test("inversionsPar works for 100000 inversed elements") {
    assert(Inversions((1 to 100000 reverse).toVector).inversionsPar(4) == (100000 / 2) * (100000 - 1))
  }

  test("inversionsForce works for single element") {
    assert(Inversions(Vector(1)).inversionsForce() == 0)
  }

  test("inversionsForce works for of non-inversed elements") {
    assert(Inversions(Vector(1, 2, 3)).inversionsForce() == 0)
  }

  test("inversionsForce works for of three two inversed elements") {
    assert(Inversions(Vector(3, 2, 1)).inversionsForce() == 3)
  }

  test("inversionsForce works for 6 inversed elements") {
    assert(Inversions(Vector(6, 5, 4, 3, 2, 1)).inversionsForce() == (6 / 2) * (6 - 1))
  }

  test("inversionsForce works for 10 inversed elements") {
    assert(Inversions(Vector(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)).inversionsForce() == (10 / 2) * (10 - 1))
  }
}
