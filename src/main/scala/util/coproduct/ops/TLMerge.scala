package util.coproduct.ops

import util.coproduct.+:
import util.coproduct.Cnil
import util.coproduct.Shape

trait TLMerge[A <: Shape, B <: Shape, O <: Shape]

object TLMerge {
  implicit def rec[H, T<: Shape, B <: Shape, O <: Shape](implicit m: TLMerge[T , B, O]): TLMerge[H  +: T, B, H +: O] = null
  implicit def cnil[B <: Shape]: TLMerge[Cnil, B, B] = null
}