package util.coproduct.ops

import shapeless.Nat
import shapeless._0
import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Coproduct
import util.coproduct.Shape
import util.typelevel.BoolOps.IF

trait Flatten[S <: Shape] {
  type Out <: Shape
  protected def _apply(i: Int, e: Coproduct[S]): Coproduct[Out]
  def apply(e: Coproduct[S]): Coproduct[Out] = _apply(0, e)
}

object Flatten {
  type Aux[S <: Shape, O <: Shape] = Flatten[S] {type Out = O}

  implicit val cnil: Aux[Cnil, Cnil] = new Flatten[Cnil] {
    type Out = Cnil
    def _apply(i: Int, e: Coproduct[Cnil]): Coproduct[Cnil] = e
  }

  implicit def dedup[H, T <: Shape, FO <: Shape, N <: Nat, IFO, O <: Shape](
    implicit f: Aux[T, FO],
    ind: IndexOf.Aux[FO, H, N],
    _if: IF.Aux[N =:= _0, Coproduct[H +: FO], Coproduct[FO], IFO],
    ev: IFO <:< Coproduct[O]
  ): Aux[H +: T, O] = new Flatten[H +: T] {

    type Out = O
    def _apply(i: Int, e: Coproduct[H +: T]): Coproduct[Out] = {
      if (e.i == i) {
        _if(
          e.asInstanceOf[Coproduct[H +: FO]],
          Coproduct(e.v, ind.i + i - 1)
        )
      } else {
        _if(
          f._apply(i + 1, e.asInstanceOf[Coproduct[T]]).asInstanceOf[Coproduct[H +: FO]],
          f._apply(i, Coproduct(e.v, e.i - 1))
        )
      }
    }
  }

  //here we build up the coproduct whose shape corresponds to merged shapes (and then dedup method deduplicates it)
  implicit def merge[H <: Shape, T <: Shape, MO <: Shape, O <: Shape](
    implicit m: TLMerge[H, T, MO],
    f: Aux[MO, O],
    eggSz: Length[H]
  ): Aux[Coproduct[H] +: T, O] = {
    new Flatten[Coproduct[H] +: T] {
      type Out = O
      def _apply(i: Int, e: Coproduct[Coproduct[H] +: T]): Coproduct[O] = {
        if (e.i == i) {
          val inner = e.v.asInstanceOf[Coproduct[H]]
          f._apply(i, Coproduct(inner.v, inner.i + i))
        }
        else {
          f._apply(i, Coproduct(e.v, e.i + eggSz.l - 1))
        }
      }
    }
  }

}