package util.coproduct.ops

import shapeless.Nat
import shapeless.Succ
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Coproduct
import util.coproduct.Shape
import util.typelevel.BoolOps.IF


trait TLExtendLeftBy[S <: Shape, S1 <: Shape, Out <: Shape, Shift <: Nat]

object TLExtendLeftBy {

  implicit def cnilR[S <: Shape, N <: Nat]: TLExtendLeftBy[S, Cnil, S, Nat._0] = null

  implicit def inject[S <: Shape, L, R <: Shape, O <: Shape, N <: Nat, N1<: Nat, TLO<: Shape, IFO<: Nat](
    implicit m: TLExtendLeftBy[S, R, O, N],
    a: TLAdd[O, L, TLO, N1],
    x: IF.Aux[TLO <:< +:[L, O], Succ[N], N, IFO]
  ): TLExtendLeftBy[S, L +: R, TLO, IFO] = null
}

class ExtendLeftBy[S <: Shape, S1 <: Shape](val i: Int) extends AnyVal{
  type Out <: Shape
  def apply(s: Coproduct[S]): Coproduct[Out] = Coproduct(s.v, s.i + i)
}

object ExtendLeftBy {
  type Aux[S <: Shape, S1 <: Shape, O <: Shape] = ExtendLeftBy[S, S1] {type Out = O}
  implicit def instance[S <: Shape, S1 <: Shape, O <: Shape, N <: Nat](
    implicit el: TLExtendLeftBy[S, S1, O, N],
    i: ToInt[N]
  ): Aux[S, S1, O] = new ExtendLeftBy[S, S1](i()).asInstanceOf[Aux[S, S1, O]]
}