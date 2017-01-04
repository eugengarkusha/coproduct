package util.coproduct.ops

import shapeless.Nat
import shapeless.Succ
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Shape

//todo: add value level TC
trait TLAddRight[S <: Shape, V, Out <: Shape, Ind <: Nat]

trait TLAddRightLow {self: TLAddRight.type =>
  implicit def cnil[V]: TLAddRight[Cnil, V, V +: Cnil, Nat._0] = null
  implicit def rec[H,T <: Shape, V,O<: Shape, N <: Nat](implicit t: TLAddRight[T, V ,O, N]): TLAddRight[H+:T, V, H +: O, Succ[N]] = null
}

object TLAddRight extends TLAddRightLow {
  implicit def inject[S <: Shape, V, N <: Nat](implicit e: TLRemove[S, V, N, _]): TLAddRight[S, V, S, N] = null
}