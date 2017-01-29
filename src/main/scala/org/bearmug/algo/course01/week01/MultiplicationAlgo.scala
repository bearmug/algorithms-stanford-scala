package org.bearmug.algo.course01.week01

class MultiplicationAlgo {

  def multiplySimple(a: Int, b: Int) = a * b

}

case class Recursive(val n: String) {

  def +(other: Recursive): Recursive = Recursive((BigInt(this.n) + BigInt(other.n)).toString())

  def *(other: String): Recursive = *(Recursive(other))

  def *(other: Recursive): Recursive = (this.n, other.n) match {
    case ("", _) => Recursive("0")
    case (_, "") => Recursive("0")
    case (thisK, thatK) if thisK.length == 1 => Recursive((thisK.toInt * thatK.toInt).toString)
    case (thisK, thatK) if thatK.length == 1 => Recursive((thisK.toInt * thatK.toInt).toString)
    case (thisK, thatK) => {
      val (a, b) = (thisK.substring(0, thisK.length / 2), thisK.substring(thisK.length / 2))
      val (c, d) = (thatK.substring(0, thatK.length / 2), thatK.substring(thatK.length / 2))
      val (ac, ad, bc, bd) = (
        Recursive(a) * Recursive(c),
        Recursive(a) * Recursive(d),
        Recursive(b) * Recursive(c),
        Recursive(b) * Recursive(d))
      ac * math.pow(10, this.n.length).toString + (ad + bc) * math.pow(10, this.n.length / 2).toString + bd
    }
  }

  override def toString: String = n.toString

  override def equals(obj: scala.Any): Boolean = obj match {
    case Recursive(num) if num == this.n => true
    case _ => false
  }

  override def hashCode(): Int = this.n.hashCode
}
