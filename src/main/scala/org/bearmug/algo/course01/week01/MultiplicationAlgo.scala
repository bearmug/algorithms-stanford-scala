package org.bearmug.algo.course01.week01

case class Recursive(val n: String) {

  def +(other: Recursive): Recursive = Recursive((BigInt(this.n) + BigInt(other.n)).toString())

  def tenPower(power: Int): Recursive = power match {
    case 0 => this
    case _ => Recursive(n + ("0" * power))
  }

  def *(other: Recursive): Recursive = (this.n, other.n) match {
    case ("", _) => Recursive("0")
    case (_, "") => Recursive("0")
    case (thisK, thatK) if thisK.length == 1 => Recursive((thisK.toInt * thatK.toInt).toString)
    case (thisK, thatK) if thatK.length == 1 => Recursive((thisK.toInt * thatK.toInt).toString)
    case (thisK, thatK) => {
      val len = thisK.length max thatK.length
      val (a, b) = (thisK.substring(0, thisK.length - len / 2), thisK.substring(thisK.length - len / 2))
      val (c, d) = (thatK.substring(0, thatK.length - len / 2), thatK.substring(thatK.length - len / 2))
      val (ac, ad, bc, bd) = (
        Recursive(a) * Recursive(c),
        Recursive(a) * Recursive(d),
        Recursive(b) * Recursive(c),
        Recursive(b) * Recursive(d))
      (ac tenPower (len / 2 * 2)) + ((ad + bc) tenPower(len / 2)) + bd
    }
  }

  override def toString: String = n.toString

  override def equals(obj: scala.Any): Boolean = obj match {
    case Recursive(num) if num == this.n => true
    case _ => false
  }

  override def hashCode(): Int = this.n.hashCode
}
