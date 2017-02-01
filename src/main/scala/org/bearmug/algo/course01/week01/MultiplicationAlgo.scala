package org.bearmug.algo.course01.week01

import scala.collection.GenSeq

/**
  * Basic operations implementation class
  *
  * @param n decimal representation for the number
  */
case class SNum(n: String) {

  /**
    * Adding for another SNum instance
    *
    * @param other one more SNum instance to add
    * @return arithmetic sum of two SNum
    */
  def +(other: SNum): SNum = SNum(BigInt(this.n) + BigInt(other.n))

  /**
    * Subtracting for another SNum instance
    *
    * @param other SNum instance to subtract to
    * @return arithmetic difference of two SNum
    */
  def -(other: SNum): SNum = SNum(BigInt(this.n) - BigInt(other.n))

  /**
    * Power of 10 calculation, implemented over simple ''000 .. 0'' string concatenation
    *
    * @param power power of 10 to apply
    * @return SNum instance
    */
  def tenPower(power: Int): SNum = SNum(this.n + ("0" * power))

  /**
    * Numbers multiplication, agnostic to used recursive or Karatsuba approach
    *
    * @param other another SNum to multiply
    * @param f     function to transform (a, b, c, d) components of two numbers to those product.
    * @return
    */
  def multiply(other: String)(f: (String, String, String, String, Int) => SNum): SNum = (this.n, other) match {
    case (aR, bR) if aR.length > bR.length => SNum(other).multiply(this.n)(f) //- get rid of swap-twins cases
    case ("", _) => SNum("0")
    case (aR, bR) if aR.length == 1 => SNum(aR.toInt * bR.toInt)
    case (aR, bR) =>
      val len = aR.length
      val (a, b) = aR.splitAt(aR.length - len / 2)
      val (c, d) = bR.splitAt(bR.length - len / 2)
      f(a, b, c, d, len)
  }

  /**
    * Mostly used for development convenience
    *
    * @return string representation of SNum
    */
  override def toString: String = n.toString //- for debug convenience
}

/**
  * Complimentary object to hold apply implementation and multiplication core algo
  */
object SNum {

  def apply(n: BigInt): SNum = SNum(n.toString())

  /**
    * Sequential multiplication implementation. It has no parallel forks.
    *
    * @param s1 1st number to multiply
    * @param s2 2nd number to multiply
    * @return multiplication product
    */
  def recursiveSeq(s1: String, s2: String): SNum = multiply(s1, s2, 0,
    (s, _) => s, //- no parallel transformation
    (a, b, c, d) => Seq((a, c), (a, d), (b, c), (b, d)))

  /**
    * Karatsuba sequential multiplication implementation. It has no parallel forks.
    *
    * @param s1 1st number to multiply
    * @param s2 2nd number to multiply
    * @return multiplication product
    */
  def karatsubaSeq(s1: String, s2: String): SNum = multiply(s1, s2, 0,
    (s, _) => s, //- no parallel transformation
    (a, b, c, d) => Seq((a, c), (b, d), ((SNum(a) + SNum(b)).toString, (SNum(c) + SNum(d)).toString)))

  /**
    * Recursive parallel multiplication implementation. It has some parallelization threshold inside.
    *
    * @param s1        1st number to multiply
    * @param s2        2nd number to multiply
    * @param parLevels fork levels to use parallel calculations
    * @return multiplication product
    */
  def recursivePar(s1: String, s2: String, parLevels: Int): SNum =
    multiply(s1, s2, parLevels,
      (s, levels) => if (levels <= 0) s else s.par,
      (a, b, c, d) => Seq((a, c), (a, d), (b, c), (b, d)))

  /**
    * Karatsuba parallel multiplication implementation. It has some parallelization threshold inside.
    *
    * @param s1        1st number to multiply
    * @param s2        2nd number to multiply
    * @param parLevels fork levels to use parallel calculations
    * @return multiplication product
    */
  def karatsubaPar(s1: String, s2: String, parLevels: Int): SNum =
    multiply(s1, s2, parLevels,
      (s, levels) => if (levels <= 0) s else s.par,
      (a, b, c, d) => Seq((a, c), (b, d), ((SNum(a) + SNum(b)).toString, (SNum(c) + SNum(d)).toString)))

  /**
    * This is very internals of implemented functional. This method could run as recursive or
    * Karatsuba implementation. It depends on passed functions.
    * @param s1 1st number to multiply
    * @param s2 2nd number to multiply
    * @param levels fork levels to use parallel calculations
    * @param t function to transform sequence of number pairs to parallel sequence potentially
    * @param s function to prepare numbers pairs to multiply them with one of the next steps
    * @return multiplication product
    */
  private def multiply(s1: String, s2: String, levels: Int,
                       t: (Seq[(String, String)], Int) => GenSeq[(String, String)],
                       s: (String, String, String, String) => Seq[(String, String)]): SNum =
    SNum(s1).multiply(s2)((a, b, c, d, len) => {
      t(s(a, b, c, d), levels).map(tup =>
        multiply(tup._1, tup._2, levels - 1, t, s)).seq match {
        case Seq(ac: SNum, ad: SNum, bc: SNum, bd: SNum) => //- recursive approach case
          (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower (len / 2)) + bd
        case Seq(ac: SNum, bd: SNum, abcd: SNum) => //- case for Karatsuba algorithm
          (ac tenPower (len / 2 * 2)) + ((abcd - ac - bd) tenPower (len / 2)) + bd
      }
    })
}
