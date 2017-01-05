package util

import shapeless.Nat
import shapeless.ops.fin.ToNat
import shapeless.ops.nat.ToInt
import util.coproduct.Coproduct
import util.coproduct.Shape
import util.coproduct.ops.At
import util.coproduct.ops.ExtendLeftBy
import util.coproduct.ops.ExtendRightBy
import util.coproduct.ops.Flatten
import util.coproduct.ops.Remove

package object coproduct {

  implicit class syntax[S <: Shape](val e: Coproduct[S]) {
    def removeElem[T](implicit r: Remove[S, T]): Either[Coproduct[r.Rest], T] = r(e)

    def extendLeftBy[K <: Shape](implicit el: ExtendLeftBy[S, K]): Coproduct[el.Out] = el(e)

    def extendRightBy[K <: Shape](implicit er: ExtendRightBy[S, K]): Coproduct[er.Out] = er(e)

    def flatten(implicit f: Flatten[S]): Coproduct[f.Out] = f(e)

    def at[N <: Nat](ind: N)(implicit x: At[S, N]): Option[x.Out] = x(e)
  }

}
