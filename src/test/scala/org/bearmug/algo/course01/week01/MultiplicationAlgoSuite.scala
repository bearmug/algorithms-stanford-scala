package org.bearmug.algo.course01.week01

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiplicationAlgoSuite extends FunSuite {
  test("recursiveSimple works for empty input") {
    assert(Recursive("") * Recursive("3422") == Recursive("0"))
    assert(Recursive("666") * Recursive("") == Recursive("0"))
    assert(Recursive("") * Recursive("") == Recursive("0"))
  }

  test("recursiveSimple works for single digit multiplication") {
    assert(Recursive("1") * Recursive("1") == Recursive("1"))
    assert(Recursive("3") * Recursive("9") == Recursive("27"))
    assert(Recursive("7") * Recursive("8") == Recursive("56"))
  }
}
