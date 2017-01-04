package util.coproduct

sealed trait Shape

sealed trait +:[H, T <: Shape] extends Shape

sealed trait Cnil extends Shape

//making constructor (and apply method) private in order to prevent the crteation of illegal instances
class Coproduct[T <: Shape] private[coproduct](val v: Any, val i: Int){
  override def toString: String = s"Coproduct(v = $v, i = $i)"
  override def equals (e: Any) = e.isInstanceOf[Coproduct[_]] && {
   val x = e.asInstanceOf[Coproduct[_]]
   x.i == i && x.v == v
  }
  override def hashCode(): Int = 7 * v.hashCode() ^  13 * i
}

object Coproduct {
  type :+:[H, H1] = H +: H1 +: Cnil
  private[coproduct] def apply[T <: Shape](v: Any, i: Int): Coproduct[T] = new Coproduct[T](v, i)
  def empty = Coproduct[Cnil](new Cnil{}, 0)
}




