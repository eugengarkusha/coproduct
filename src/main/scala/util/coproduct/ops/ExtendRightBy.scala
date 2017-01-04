package util.coproduct.ops

import shapeless.Nat
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Coproduct
import util.coproduct.Shape

trait ExtendRightBy[S <: Shape, S1 <: Shape] {
  type Out <: Shape
  def apply(s: Coproduct[S]): Coproduct[Out] = s.asInstanceOf[Coproduct[Out]]
}

object ExtendRightBy {

  type Aux[S <: Shape, S1 <: Shape, O <: Shape] = ExtendRightBy[S, S1] {type Out = O}

  val inst: Aux[Shape, Shape, Shape] = new ExtendRightBy[Shape, Shape] {type Out = Shape}

  implicit def cnilR[S <: Shape]: Aux[S, Cnil, S] = null

  implicit def inject[S <: Shape, L1, R1 <: Shape, O <: Shape, TLO <: Shape](
    implicit a: TLAddRight[S, L1, TLO, _],
    m: Aux[TLO, R1, O]
  ): Aux[S, L1 +: R1, O] = inst.asInstanceOf[Aux[S, L1 +: R1, O]]
}