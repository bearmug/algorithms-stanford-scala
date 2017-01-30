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

  test("recursiveSimple works for 2 digit multiplication") {
    assert(Recursive("11") * Recursive("40") == Recursive("440"))
    assert(Recursive("27") * Recursive("51") == Recursive("1377"))
  }

  test("recursiveSimple works for even length numbers") {
    assert(Recursive("1234") * Recursive("4321") == Recursive("5332114"))
    assert(Recursive("99999999") * Recursive("88888888") ==
      Recursive((BigInt("99999999") * BigInt("88888888")).toString()))
  }

  test("recursiveSimple works for odd length numbers") {
    assert(Recursive("111") * Recursive("111") == Recursive("12321"))
    assert(Recursive("23456") * Recursive("12345") == Recursive("289564320"))
  }

  test("recursiveSimple works different length numbers") {
    assert(Recursive("111") * Recursive("23") == Recursive("2553"))
    assert(Recursive("11") * Recursive("221") == Recursive("2431"))
  }


  test("recursiveSimple works for target length numbers") {
    assert(
      Recursive("3141592653589793238462643383279502884197169399375105820974944592") *
        Recursive("2718281828459045235360287471352662497757247093699959574966967627") ==
        Recursive("8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184"))
  }
}
