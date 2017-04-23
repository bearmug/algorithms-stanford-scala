package org.bearmug.algo.course02.week03

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.io.Source
import scala.util.Random

import MFactory._

@RunWith(classOf[JUnitRunner])
class MedianSuite extends FunSuite {

  test("calc works for empty data") {
    assert(dualHeap().calc == Int.MaxValue)
  }

  test("calc works for single element") {
    assert((dualHeap() + 1).calc == 1)
  }

  test("calc works for two elements") {
    assert((dualHeap() + 1 + 3).calc == 1)
    assert((dualHeap() + 5 + 3).calc == 3)
  }

  test("calc works for two same elements") {
    assert((dualHeap() + 1 + 1).calc == 1)
    assert((dualHeap() + 4 + 1 + 3 + 3).calc == 3)
  }

  test("calc works for three elements") {
    assert((dualHeap() + 1 + 3 + 2).calc == 2)
    assert((dualHeap() + 3 + 2 + 1).calc == 2)
    assert((dualHeap() + 2 + 3 + 1).calc == 2)
  }

  test("calc works for four elements") {
    assert((dualHeap() + 1 + 3 + 2 + 4).calc == 2)
    assert((dualHeap() + 5 + 3 + 4 + 2).calc == 3)
  }

  test("calc works for five elements") {
    assert((dualHeap() + 1 + 3 + 2 + 4 + 5).calc == 3)
    assert((dualHeap() + 5 + 3 + 4 + 2 + 1).calc == 3)
  }

  test("calc works for empty list") {
    assert((dualHeap() ++ List.empty).calc == Int.MaxValue)
    assert((dualHeap() ++ List.empty).calc == Int.MaxValue)
  }

  test("calc works for non-empty list") {
    assert((dualHeap() ++ List(3, 2, 5, 4, 1)).calc == 3)
    assert((singleHeap() ++ List(3, 2, 5, 4, 1)).calc == 3)
    assert((dualHeap() ++ List(3, 2, 5, 6, 4, 1)).calc == 3)
    assert((singleHeap() ++ List(3, 2, 5, 6, 4, 1)).calc == 3)
  }

  test("calc works for N elements") {
    for (i <- 2 to 1000){
      val data: Vector[Int] = Seq.fill(i)(Random.nextInt(100000)).toSet.toVector
      val goal = data.sorted.apply((data.length - 1) / 2)
      assert(data.foldLeft(dualHeap())((m, i) => m + i).calc == goal,
        s", \nfailed for dualHeap, length: $i," +
          s"\ninput: $data")
      assert(data.foldLeft(singleHeap())((m, i) => m + i).calc == goal,
        s", \nfailed for singleHeap, length: $i," +
          s"\ninput: $data")
      assert(data.foldLeft(plain())((m, i) => m + i).calc == goal,
        s", \nfailed for plain, length: $i," +
          s"\ninput: $data")
    }
  }

  test("medianSum works for single element") {
    assert(medianSum(List(1))(dualHeap()) == 1)
    assert(medianSum(List(1))(singleHeap()) == 1)
    assert(medianSum(List(1))(plain()) == 1)
  }

  test("medianSum works for elements list") {
    assert(medianSum(List(3, 1, 2, 4, 0, 5))(dualHeap()) == 12)
    assert(medianSum(List(0, 1, 2, 3, 4, 5))(dualHeap()) == 6)
    assert(medianSum(List(5, 4, 3, 2, 1, 0))(dualHeap()) == 21)

    assert(medianSum(List(3, 1, 2, 4, 0, 5))(singleHeap()) == 12)
    assert(medianSum(List(0, 1, 2, 3, 4, 5))(singleHeap()) == 6)
    assert(medianSum(List(5, 4, 3, 2, 1, 0))(singleHeap()) == 21)

    assert(medianSum(List(3, 1, 2, 4, 0, 5))(plain()) == 12)
    assert(medianSum(List(0, 1, 2, 3, 4, 5))(plain()) == 6)
    assert(medianSum(List(5, 4, 3, 2, 1, 0))(plain()) == 21)
  }
}
