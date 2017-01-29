package org.bearmug.algo.course01.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiplicationAlgoSuite extends FunSuite {
  test("multiplySimple just works") {

    assert(new MultiplicationAlgo().multiplySimple(3, 5) == 15)
  }
}
