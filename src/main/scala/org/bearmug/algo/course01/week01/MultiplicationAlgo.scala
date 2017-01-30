package org.bearmug.algo.course01.week01

case class Recursive(n: String) {

  def +(other: Recursive): Recursive = Recursive((BigInt(this.n) + BigInt(other.n)).toString())

  def tenPower(power: Int): Recursive = power match {
    case 0 => this
    case _ => Recursive(n + ("0" * power))
  }

  def *(other: Recursive): Recursive = (this.n, other.n) match {
    case (aR, bR) if aR.length > bR.length => other * this
    case ("", _) => Recursive("0")
    case (aR, bR) if aR.length == 1 => Recursive((aR.toInt * bR.toInt).toString)
    case (aR, bR) =>
      val len = aR.length
      val (a, b) = (aR.substring(0, aR.length - len / 2), aR.substring(aR.length - len / 2))
      val (c, d) = (bR.substring(0, bR.length - len / 2), bR.substring(bR.length - len / 2))
      val (ac, ad, bc, bd) = (
        Recursive(a) * Recursive(c),
        Recursive(a) * Recursive(d),
        Recursive(b) * Recursive(c),
        Recursive(b) * Recursive(d))
      (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower (len / 2)) + bd
  }

  override def toString: String = n.toString

  override def equals(obj: scala.Any): Boolean = obj match {
    case Recursive(num) if num == this.n => true
    case _ => false
  }

  override def hashCode(): Int = this.n.hashCode
}
