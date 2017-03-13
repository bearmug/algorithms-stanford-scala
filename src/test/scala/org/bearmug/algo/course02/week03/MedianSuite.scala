package org.bearmug.algo.course02.week03

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class MedianSuite extends FunSuite {

  test("calc works for empty data") {
    assert(Median().calc == Int.MaxValue)
  }

  test("calc works for single element") {
    assert((Median() + 1).calc == 1)
  }

  test("calc works for two elements") {
    assert((Median() + 1 + 3).calc == 3)
    assert((Median() + 5 + 3).calc == 5)
  }

  test("calc works for two same elements") {
    assert((Median() + 1 + 1).calc == 1)
    assert((Median() + 4 + 1 + 3 + 3).calc == 3)
  }

  test("calc works for three elements") {
    assert((Median() + 1 + 3 + 2).calc == 2)
    assert((Median() + 3 + 2 + 1).calc == 2)
    assert((Median() + 2 + 3 + 1).calc == 2)
  }

  test("calc works for four elements") {
    assert((Median() + 1 + 3 + 2 + 4).calc == 3)
    assert((Median() + 5 + 3 + 4 + 2).calc == 4)
  }

  test("calc works for five elements") {
    assert((Median() + 1 + 3 + 2 + 4 + 5).calc == 3)
    assert((Median() + 5 + 3 + 4 + 2 + 1).calc == 3)
  }

  test("calc works for even elements") {
    for (i <- 2 to 1000){
      val data: Vector[Int] = Seq.fill(i)(Random.nextInt(100000)).toSet.toVector
      assert(data.sorted.apply(data.length/2) == data.foldLeft(Median())((m, i) => m + i).calc,
        s", \nlength: $i," +
          s"\ninput: $data")
    }
  }
}
