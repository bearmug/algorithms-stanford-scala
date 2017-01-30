package org.bearmug.algo.course01.week01

import org.bearmug.algo.course01.week01.Recursive.multiplyRecursive
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiplicationAlgoSuite extends FunSuite {
  test("multiplyRecursive works for empty input") {
    assert(multiplyRecursive("", "3422") == "0")
    assert(multiplyRecursive("666", "") == "0")
    assert(multiplyRecursive("", "") == "0")
  }

  test("recursiveSimple works for single digit multiplication") {
    assert(multiplyRecursive("1", "1") == "1")
    assert(multiplyRecursive("3", "9") == "27")
    assert(multiplyRecursive("7", "8") == "56")
  }

  test("recursiveSimple works for 2 digit multiplication") {
    assert(multiplyRecursive("11", "40") == "440")
    assert(multiplyRecursive("27", "51") == "1377")
  }

  test("recursiveSimple works for even length numbers") {
    assert(multiplyRecursive("1234", "4321") == "5332114")
    assert(multiplyRecursive("99999999", "88888888") ==
      (BigInt("99999999") * BigInt("88888888")).toString())
  }

  test("recursiveSimple works for odd length numbers") {
    assert(multiplyRecursive("111", "111") == "12321")
    assert(multiplyRecursive("23456", "12345") == "289564320")
  }

  test("recursiveSimple works different length numbers") {
    assert(multiplyRecursive("111", "23") == "2553")
    assert(multiplyRecursive("11", "221") == "2431")
  }


  test("recursiveSimple works for target length numbers") {
    assert(
      multiplyRecursive("3141592653589793238462643383279502884197169399375105820974944592",
        "2718281828459045235360287471352662497757247093699959574966967627") ==
        "8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184")
  }
}
