package org.bearmug.algo.course01.week01

case class Recursive(n: String) {

  def +(other: Recursive): Recursive = Recursive((BigInt(this.n) + BigInt(other.n)).toString())

  def -(other: Recursive): Recursive = Recursive((BigInt(this.n) - BigInt(other.n)).toString())

  def tenPower(power: Int): Recursive = power match {
    case 0 => this
    case _ => Recursive(n + ("0" * power))
  }

  def multiply(other: Recursive)(f: (String, String, String, String, Int) => Recursive): Recursive = (this.n, other.n) match {
    case (aR, bR) if aR.length > bR.length => other.multiply(this)(f)
    case ("", _) => Recursive("0")
    case (aR, bR) if aR.length == 1 => Recursive((aR.toInt * bR.toInt).toString)
    case (aR, bR) =>
      val len = aR.length
      val (a, b) = (aR.substring(0, aR.length - len / 2), aR.substring(aR.length - len / 2))
      val (c, d) = (bR.substring(0, bR.length - len / 2), bR.substring(bR.length - len / 2))
      f(a, b, c, d, len)
  }

  override def toString: String = n.toString
}

object Recursive {
  def multiplyRecursive(s1: String, s2: String): Recursive =
    Recursive(s1).multiply(Recursive(s2))((a, b, c, d, len) => {
      val (ac, ad, bc, bd) = (
        multiplyRecursive(a, c),
        multiplyRecursive(a, d),
        multiplyRecursive(b, c),
        multiplyRecursive(b, d))
      (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower (len / 2)) + bd
    })

  def multiplyKaratsuba(s1: String, s2: String): Recursive =
    Recursive(s1).multiply(Recursive(s2))((a, b, c, d, len) => {
      val (ac, bd, abcd) = (
        multiplyKaratsuba(a, c),
        multiplyKaratsuba(b, d),
        multiplyKaratsuba((Recursive(a) + Recursive(b)).toString, (Recursive(c) + Recursive(d)).toString))
      (ac tenPower (len / 2 * 2)) + ((abcd - ac - bd) tenPower (len / 2)) + bd
    })
}
