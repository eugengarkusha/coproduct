package util.coproduct.ops

import shapeless.Nat
import shapeless.ops.nat.ToInt
import util.coproduct.Coproduct
import util.coproduct.Shape

class Inject[S <: Shape, V, N](val i: Int) extends AnyVal {
 def apply(v: V): Coproduct[S] = Coproduct[S](v, i)
}
object Inject{
  class InjectSyntax[S <: Shape, V](val  v:V){
    def at[N <: Nat](ind: N)(implicit inj: Inject[S, V, N]) = inj(v)
  }
  def apply[S <: Shape, V](v: V) =  new InjectSyntax[S, V](v)
  implicit def inst[S<: Shape, V, N<: Nat] (implicit at: TLAt[S, N, V], i: ToInt[N]): Inject[S, V, N] = new Inject[S, V, N](i())
}
