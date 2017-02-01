package org.bearmug.algo.course01.week01

import scala.collection.parallel.ParSeq

case class Recursive(n: String) {

  def +(other: Recursive): Recursive = Recursive(BigInt(this.n) + BigInt(other.n))
  def -(other: Recursive): Recursive = Recursive(BigInt(this.n) - BigInt(other.n))
  def tenPower(power: Int): Recursive = Recursive(this.n + ("0" * power))

  def multiply(other: String)(f: (String, String, String, String, Int) => Recursive): Recursive = (this.n, other) match {
    case (aR, bR) if aR.length > bR.length => Recursive(other).multiply(this.n)(f) //- get rid of swap-twins cases
    case ("", _) => Recursive("0")
    case (aR, bR) if aR.length == 1 => Recursive(aR.toInt * bR.toInt)
    case (aR, bR) =>
      val len = aR.length
      val (a, b) = aR.splitAt(aR.length - len / 2)
      val (c, d) = bR.splitAt(bR.length - len / 2)
      f(a, b, c, d, len)
  }

  override def toString: String = n.toString
}

object Recursive {

  def apply(n: BigInt): Recursive = Recursive(n.toString())

  def multiplyRecursive(s1: String, s2: String): Recursive = parMultiplyRecursive(s1, s2, 0)

  def multiplyKaratsuba(s1: String, s2: String): Recursive = parMultiplyKaratsuba(s1, s2, 0)

  def parMultiplyRecursive(s1: String, s2: String, threshold: Int): Recursive =
    Recursive(s1).multiply(s2)((a, b, c, d, len) => {
      val seq = if (threshold <= 0) Seq((a, c), (a, d), (b, c), (b, d))
      else ParSeq((a, c), (a, d), (b, c), (b, d))
      val output = seq.map(t => parMultiplyRecursive(t._1, t._2, threshold - 1)).seq
      output match {
        case Seq(ac: Recursive, ad: Recursive, bc: Recursive, bd: Recursive) =>
          (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower (len / 2)) + bd
      }
    })

  def parMultiplyKaratsuba(s1: String, s2: String, threshold: Int): Recursive =
    Recursive(s1).multiply(s2)((a, b, c, d, len) => {
      val seq = if (threshold <= 0) Seq((a, c), (b, d), ((Recursive(a) + Recursive(b)).toString, (Recursive(c) + Recursive(d)).toString))
      else ParSeq((a, c), (b, d), ((Recursive(a) + Recursive(b)).toString, (Recursive(c) + Recursive(d)).toString))
      val output = seq.map(t => parMultiplyKaratsuba(t._1, t._2, threshold - 1)).seq
      output match {
        case Seq(ac: Recursive, bd: Recursive, abcd: Recursive) =>
          (ac tenPower (len / 2 * 2)) + ((abcd - ac - bd) tenPower (len / 2)) + bd
      }
    })
}
