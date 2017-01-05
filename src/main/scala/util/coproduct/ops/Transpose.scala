package util.coproduct.ops

import shapeless.<:!<
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Coproduct
import util.coproduct.Shape

//same idea as in shapeless.hlist.ops.ZipOne (do not be confused with shapeless.coproduct.ops.ZipOne)
trait TLZipOne[S <: Shape, S1 <: Shape, Out <: Shape]

object TLZipOne {
  implicit def cnil1[H <: Shape]: TLZipOne[H, Cnil, Cnil] = null
  implicit def cnil2[H <: Shape]: TLZipOne[Cnil, H, Cnil] = null
  implicit def head[H, S <: Shape]: TLZipOne[H +: Cnil, Coproduct[S] +: Cnil, Coproduct[H +: S] +: Cnil] = null
  implicit def rec[HH, HT <: Shape, TH <: Shape, TT <: Shape, O <: Shape](
    implicit z: TLZipOne[HT, TT, O]
  ): TLZipOne[HH +: HT, Coproduct[TH] +: TT, Coproduct[HH +: TH] +: O] = null
}

trait TLMapConst[S <: Shape, C, Out <: Shape]

object TLMapConst {
  implicit def cnil[C]: TLMapConst[Cnil, C, Cnil] = null
  implicit def rec[H, T <: Shape, C, O <: Shape](implicit m: TLMapConst[T, C, O]): TLMapConst[H +: T, C, C +: O] = null
}

trait TLTranspose[S <: Shape, Out <: Shape]

object TLTranspose {
  implicit def cnil: TLTranspose[Cnil, Cnil] = null

  implicit def last[S <: Shape, MO <: Shape, ZO <: Shape](
    implicit  mc: TLMapConst[S, Coproduct[Cnil], MO], zo: TLZipOne[S, MO, ZO]
  ): TLTranspose[Coproduct[S] +: Cnil, ZO] = null

  implicit def rec[H <: Shape, T <: Shape, TO <: Shape, ZO <: Shape](
    implicit t: TLTranspose[T, TO],
    ev: T<:!< Cnil  ,
    zo: TLZipOne[H, TO, ZO]
  ): TLTranspose[Coproduct[H] +: T, ZO] = null
}

class Transpose[S <: Shape] {
  type Out <: Shape
  def apply(outer: Coproduct[S]): Coproduct[Out] = {
    val inner = outer.v.asInstanceOf[Coproduct[_]]
    //swapping indicies of a row and a column
    Coproduct(Coproduct(inner.v, outer.i), inner.i).asInstanceOf[Coproduct[Out]]
  }
}

object Transpose {
  type Aux[S <: Shape, O<: Shape] = Transpose[S]{type Out = O}
  val _inst = new Transpose[Shape]
  implicit def inst[S <: Shape, O <: Shape](implicit t: TLTranspose[S, O]): Aux[S, O] = _inst.asInstanceOf[Aux[S, O]]
}