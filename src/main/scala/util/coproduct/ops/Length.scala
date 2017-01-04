package util.coproduct.ops

import shapeless.Nat
import shapeless.Succ
import shapeless._0
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Shape


trait TLLength[S <: Shape, Out <: Nat]

object TLLength {
  implicit def nil: TLLength[Cnil, _0] = null
  implicit def rec[H, T <: Shape, N <: Nat](implicit s: TLLength[T, N]): TLLength[H +: T, Succ[N]] = null
}

trait Length[S <: Shape] {
  type Out <: Nat
  val l: Int
}

object Length {
  type Aux[S <: Shape, O <: Nat] = Length[S]{type Out = O}
  implicit def inst[S<: Shape, N<: Nat](implicit l: TLLength[S, N], _l: ToInt[N]) = new Length[S] {
    type Out = N
    val l = _l()
  }
}