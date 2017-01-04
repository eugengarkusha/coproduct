package util.coproduct.ops

import shapeless.=:!=
import shapeless.Nat
import shapeless.Succ
import shapeless._0
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Shape

//1-based, 0 - absent
trait TLIndexOf[S<: Shape, V, I <: Nat]

object TLIndexOf {
  implicit def rec[H, T <: Shape, V, N<: Nat](implicit i: TLIndexOf[T, V, N], ev : N =:!= _0): TLIndexOf[H +: T, V, Succ[N]] = null
  implicit def notFound[H, T <: Shape, V](implicit i: TLIndexOf[T, V, _0], ev : V =:!= H ): TLIndexOf[H +: T, V, _0] = null
  implicit def found[V, T <: Shape]: TLIndexOf[V +: T, V, Nat._1] = null
  implicit def nil[V]: TLIndexOf[Cnil, V, _0] = null

}

trait IndexOf[S <: Shape, V] {
  type Ind <: Nat
  val i: Int
}

object IndexOf {
  type Aux [S<: Shape, V, I <: Nat] = IndexOf[S, V]{type Ind = I}

  implicit def inst[S <: Shape, V, I <: Nat](implicit tli: TLIndexOf[S, V, I], ti: ToInt[I]):Aux[S, V, I] = new IndexOf[S, V] {
    type Ind = I
    val i = ti()
  }

  def apply[S<: Shape, V](implicit io: IndexOf[S, V]) = io.i
}