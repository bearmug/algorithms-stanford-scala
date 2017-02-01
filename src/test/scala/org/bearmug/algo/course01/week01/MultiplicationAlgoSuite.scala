package org.bearmug.algo.course01.week01

import org.bearmug.algo.course01.week01.SNum.{karatsubaSeq, recursiveSeq, recursivePar, karatsubaPar}
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MultiplicationAlgoSuite extends FunSuite {
  test("multiplyRecursive works for empty input") {
    assert(recursiveSeq("", "3422").toString == "0")
    assert(recursiveSeq("666", "").toString == "0")
    assert(recursiveSeq("", "").toString == "0")
  }

  test("multiplyRecursive works for single digit multiplication") {
    assert(recursiveSeq("1", "1").toString == "1")
    assert(recursiveSeq("3", "9").toString == "27")
    assert(recursiveSeq("7", "8").toString == "56")
  }

  test("multiplyRecursive works for 2 digit multiplication") {
    assert(recursiveSeq("11", "40").toString == "440")
    assert(recursiveSeq("27", "51").toString == "1377")
  }

  test("multiplyRecursive works for even length numbers") {
    assert(recursiveSeq("1234", "4321").toString == "5332114")
    assert(recursiveSeq("99999999", "88888888").toString ==
      (BigInt("99999999") * BigInt("88888888")).toString())
  }

  test("multiplyRecursive works for odd length numbers") {
    assert(recursiveSeq("111", "111").toString == "12321")
    assert(recursiveSeq("23456", "12345").toString == "289564320")
  }

  test("multiplyRecursive works different length numbers") {
    assert(recursiveSeq("111", "23").toString == "2553")
    assert(recursiveSeq("11", "221").toString == "2431")
  }


  test("multiplyRecursive works for target length numbers") {
    assert(
      recursiveSeq("3141592653589793238462643383279502884197169399375105820974944592",
        "2718281828459045235360287471352662497757247093699959574966967627").toString ==
        "8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184")
  }

  test("multiplyKaratsuba works for empty input") {
    assert(karatsubaSeq("", "3422").toString == "0")
    assert(karatsubaSeq("666", "").toString == "0")
    assert(karatsubaSeq("", "").toString == "0")
  }

  test("multiplyKaratsuba works for single digit multiplication") {
    assert(karatsubaSeq("1", "1").toString == "1")
    assert(karatsubaSeq("3", "9").toString == "27")
    assert(karatsubaSeq("7", "8").toString == "56")
  }

  test("multiplyKaratsuba works for 2 digit multiplication") {
    assert(karatsubaSeq("11", "40").toString == "440")
    assert(karatsubaSeq("27", "51").toString == "1377")
  }

  test("multiplyKaratsuba works for even length numbers") {
    assert(karatsubaSeq("1234", "4321").toString == "5332114")
    assert(karatsubaSeq("99999999", "88888888").toString ==
      (BigInt("99999999") * BigInt("88888888")).toString())
  }

  test("multiplyKaratsuba works for odd length numbers") {
    assert(karatsubaSeq("111", "111").toString == "12321")
    assert(karatsubaSeq("23456", "12345").toString == "289564320")
  }

  test("multiplyKaratsuba works different length numbers") {
    assert(karatsubaSeq("111", "23").toString == "2553")
    assert(karatsubaSeq("11", "221").toString == "2431")
  }


  test("multiplyKaratsuba works for target length numbers") {
    assert(
      karatsubaSeq("3141592653589793238462643383279502884197169399375105820974944592",
        "2718281828459045235360287471352662497757247093699959574966967627").toString ==
        "8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184")
  }

  test("parMultiplyRecursive works for target length numbers") {
    assert(
      recursivePar("3141592653589793238462643383279502884197169399375105820974944592",
        "2718281828459045235360287471352662497757247093699959574966967627", 3).toString ==
        "8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184")
  }

  test("parMultiplyKaratsuba works for target length numbers") {
    assert(
      karatsubaPar("3141592653589793238462643383279502884197169399375105820974944592",
        "2718281828459045235360287471352662497757247093699959574966967627", 3).toString ==
        "8539734222673567065463550869546574495034888535765114961879601127" +
          "067743044893204848617875072216249073013374895871952806582723184")
  }
}
