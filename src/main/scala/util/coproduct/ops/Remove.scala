package util.coproduct.ops

import shapeless.Nat
import shapeless.Succ
import shapeless._0
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Coproduct
import util.coproduct.Shape


trait TLRemove[S <: Shape, T, Ind <: Nat, TOut <: Shape]

object TLRemove {

  implicit def rec[H, T <: Shape, V, N <: Nat, TO <: Shape](
    implicit s: TLRemove[T, V, N, TO]
  ): TLRemove[H +: T, V, Succ[N], H +: TO] = null

  implicit def found[T <: Shape, V, N <: Nat]: TLRemove[V +: T, V, _0, T] = null
}

class Remove[S <: Shape, V](val i: Int) extends AnyVal {
  type Rest <: Shape
  def apply(e: Coproduct[S]): Either[Coproduct[Rest], V]= {
    if (i == e.i) {
      Right(e.v.asInstanceOf[V])
    } else if (i < e.i) {
      Left(Coproduct(e.v, e.i - 1))
    } else {
      Left(e.asInstanceOf[Coproduct[Rest]])
    }
  }
}

object Remove {

  type Aux[S <: Shape, V, TO <: Shape] = Remove[S, V] {type Rest = TO}

  implicit def inst[S <: Shape, V, N <: Nat, TO <: Shape](implicit tle: TLRemove[S, V, N, TO], i: ToInt[N]): Aux[S, V, TO] = {
    new Remove[S, V](i()).asInstanceOf[Aux[S, V, TO]]
  }

  class ExtrSynt[V] {
    def from[S <: Shape](s: Coproduct[S])(implicit e: Remove[S, V]): Either[Coproduct[e.Rest], V] = e.apply(s)
  }

  def apply[V] = new ExtrSynt[V]
}