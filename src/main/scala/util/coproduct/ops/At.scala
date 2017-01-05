package util.coproduct.ops

import shapeless.Nat
import shapeless._0
import shapeless.ops.nat.Pred
import shapeless.ops.nat.ToInt
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Coproduct
import util.coproduct.Shape

trait TLAt[S <: Shape, N <: Nat, Out]

trait TLAtLow {
  implicit def found[H, T <: Shape]: TLAt[H +: T, _0, H] = null
}

object TLAt extends TLAtLow {
  implicit def cnil: TLAt[Cnil, _0, Cnil] = null
  implicit def rec[H, T <: Shape, N <: Nat, N1 <: Nat, O](implicit p: Pred.Aux[N, N1], at: TLAt[T, N1, O]): TLAt[H +: T, N, O] = null
}

class At[S <: Shape, N <: Nat](val i: Int) extends AnyVal {
  type Out
  def apply(s: Coproduct[S]): Option[Out] = if (s.i == i) Some(s.v.asInstanceOf[Out]) else None
}

object At {
  implicit def inst[S <: Shape, N <: Nat, O](implicit at: TLAt[S, N, O], i: ToInt[N]): At[S, N] {type Out = O} = {
    new At[S, N](i()).asInstanceOf[At[S, N] {type Out = O}]
  }
}