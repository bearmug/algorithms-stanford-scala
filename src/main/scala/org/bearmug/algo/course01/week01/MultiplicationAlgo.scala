package org.bearmug.algo.course01.week01

import scala.collection.GenSeq

case class SNum(n: String) {

  def +(other: SNum): SNum = SNum(BigInt(this.n) + BigInt(other.n))

  def -(other: SNum): SNum = SNum(BigInt(this.n) - BigInt(other.n))

  def tenPower(power: Int): SNum = SNum(this.n + ("0" * power))

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

  override def toString: String = n.toString
}

object SNum {

  def apply(n: BigInt): SNum = SNum(n.toString())

  def recursiveSeq(s1: String, s2: String): SNum = multiply(s1, s2, 0,
    (s, _) => s,
    (a, b, c, d) => Seq((a, c), (a, d), (b, c), (b, d)))

  def karatsubaSeq(s1: String, s2: String): SNum = multiply(s1, s2, 0,
    (s, _) => s,
    (a, b, c, d) =>Seq((a, c), (b, d), ((SNum(a) + SNum(b)).toString, (SNum(c) + SNum(d)).toString)))

  def recursivePar(s1: String, s2: String, parLevels: Int): SNum =
    multiply(s1, s2, parLevels,
      (s, levels) => if (levels <= 0) s else s.par,
      (a, b, c, d) => Seq((a, c), (a, d), (b, c), (b, d)))

  def karatsubaPar(s1: String, s2: String, parLevels: Int): SNum =
    multiply(s1, s2, parLevels,
      (s, levels) => if (levels <= 0) s else s.par,
      (a, b, c, d) =>Seq((a, c), (b, d), ((SNum(a) + SNum(b)).toString, (SNum(c) + SNum(d)).toString)))

  private def multiply(s1: String, s2: String, levels: Int,
                                   t: (Seq[(String, String)], Int) => GenSeq[(String, String)],
                                   s: (String, String, String, String) => Seq[(String, String)]): SNum =
    SNum(s1).multiply(s2)((a, b, c, d, len) => {
      t(s(a, b, c, d), levels).map(tup =>
        multiply(tup._1, tup._2, levels - 1, t, s)).seq match {
        case Seq(ac: SNum, ad: SNum, bc: SNum, bd: SNum) =>
          (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower (len / 2)) + bd
        case Seq(ac: SNum, bd: SNum, abcd: SNum) =>
          (ac tenPower (len / 2 * 2)) + ((abcd - ac - bd) tenPower (len / 2)) + bd
      }
    })
}
