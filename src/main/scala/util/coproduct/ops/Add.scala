package util.coproduct.ops

import shapeless.Nat
import shapeless.ops.nat.Pred
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Coproduct
import util.coproduct.Shape

trait TLAdd[S <: Shape, V, Out <: Shape, Ind <: Nat]

trait TLAddLow {self: TLAdd.type =>
  implicit def prepend[S <: Shape, V]: TLAdd[S, V, V +: S, Nat._0] = null
}

object TLAdd extends TLAddLow {
  implicit def inject[S <: Shape, V, N <: Nat, N1 <: Nat](implicit e: TLIndexOf[S, V, N], p: Pred.Aux[N, N1]): TLAdd[S, V, S, N1] = null
}

trait Add[S <: Shape, V] {
  type Out <: Shape
  def apply(v: V): Coproduct[Out]
}

object Add {

  type Aux[S <: Shape, V, O <: Shape] = Add[S, V] {type Out = O}

  implicit def mainInst[S <: Shape, V, N <: Nat, O <: Shape](
    implicit add: TLAdd[S, V, O, N],
    i: ToInt[N]
  ): Aux[S, V, O] = new Add[S, V] {
    type Out = O
    def apply(v: V): Coproduct[Out] = Coproduct(v, i())
  }

  class AddSynt[V](val v: V) extends AnyVal {
    def to[S <: Shape](implicit a: Add[S, V]): Coproduct[a.Out] = a(v)
  }

  def apply[V](v: V) = new AddSynt(v)

}