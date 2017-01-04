package util.coproduct

sealed trait Shape

sealed trait +:[H, T <: Shape] extends Shape

sealed trait Cnil extends Shape

//making constructor (and apply method) private in order to prevent the crteation of illegal instances
class Coproduct[T <: Shape] private[coproduct](val v: Any, val i: Int){
  override def toString: String = s"Coproduct(v = $v, i = $i)"
  override def equals (e: Any) = e.isInstanceOf[Coproduct[_]] && {
   val x = e.asInstanceOf[Coproduct[_]]
   x.i == i && x.v == v
  }
  override def hashCode(): Int = 7 * v.hashCode() ^  13 * i
}

object Coproduct {
  type :+:[H, H1] = H +: H1 +: Cnil
  private[coproduct] def apply[T <: Shape](v: Any, i: Int): Coproduct[T] = new Coproduct[T](v, i)
  def empty = Coproduct[Cnil](new Cnil{}, 0)
}











































//trait TypeLevelMerge[S , S1]{
//  type Out <: Shape
//}
//object TypeLevelMerge{
//  type Aux [S, S1, O <: Shape] = TypeLevelMerge[S, S1]{type Out = O}
//  implicit def cnilL[S <: Shape]: Aux[SN, S, S] = null
//  implicit def cnilR[S <: Shape]: Aux[S, SN, S] = null
//  implicit def rec[L, R <: Shape, L1, R1 <: Shape, O <: Shape](implicit m: Aux[R, L1 +: R1, O]): Aux[L +: R, L1 +: R1, L  +: O] = null
//}
//
//trait Merge[S <: Shape, S1 <: Shape]{
//  type Out <: Shape
//  def append(s: Egg[S]): Egg[Out]
//  def prepend(s: Egg[S1]): Egg[Out]
//}
//
//object Merge{
//  type Aux [S <: Shape, S1 <: Shape, O <: Shape] = Merge[S, S1]{type Out = O}
//  implicit def inst[S <: Shape, S1 <: Shape, O <: Shape, N <: Nat](
//    implicit  m: TypeLevelMerge.Aux[S, S1, O],
//    s: Length.Aux[S, N],
//    i: ToInt[N]
//  ): Aux[S, S1, O]= new Merge[S, S1] {
//    type Out = O
//    def append(s: Egg[S]): Egg[Out] = s.asInstanceOf[Egg[Out]]
//    def prepend(s: Egg[S1]): Egg[Out] = Egg(s.v, s.i + i())
//  }
//}

//trait TypeLevelMerge[S<: Shape, S1<: Shape, Out <: Shape, Shift <: Nat]
//
//object TypeLevelMerge  {
//
//  implicit def cnilL[S <: Shape, N <: Nat]: TypeLevelMerge[SN, S, S, Nat._1] = null
//  implicit def cnilR[S <: Shape, N <: Nat]: TypeLevelMerge[S, SN, S, Nat._1] = null
//
//  implicit def inject[L, R <: Shape, L1, R1 <: Shape, O <: Shape, N <: Nat, N1<: Nat, TLO<: Shape, IFO<: Nat](
//    implicit m: TypeLevelMerge[R, L1 +: R1, O, N],
//    a: TypelevelAdd[O, L, TLO, N1],
//    x: IF.Aux[TLO <:< +:[L, O], Succ[N], N, IFO]
//  ): TypeLevelMerge[L +: R, L1 +: R1, TLO, IFO] = null
//}















//trait Merge[S <: Shape, S1 <: Shape] {
//  type AO <: Shape
//  type PO <: Shape
//  def append(s: Egg[S]): Egg[AO]
//  def prepend(s: Egg[S1]): Egg[PO]
//}
//
//object Merge {
//  type Aux[S <: Shape, S1 <: Shape, O1 <: Shape, O2 <: Shape] = Merge[S, S1] {type AO = O1; type PO = O2}
//  implicit def inst[S <: Shape, S1 <: Shape, _AO <: Shape, _PO <: Shape, N <: Nat](
//    implicit m: TLExtendRightBy[S, S1, _AO],
//    m1: TLExtendLeftBy[S, S1, _PO, N],
//    i: ToInt[N]
//  ): Aux[S, S1, _AO, _PO] = new Merge[S, S1] {
//    type AO = _AO
//    type PO = _PO
//    def append(s: Egg[S]): Egg[AO] = s.asInstanceOf[Egg[AO]]
//    def prepend(s: Egg[S1]): Egg[PO] = Egg(s.v, s.i + i())
//  }
//}

//trait TypeLevelFlatten:
//
//trait Flatten[S <: Shape] {
//  type Out <: Shape
//  def apply(s: S): Out
//}
//
//object Flatten{
//  type Aux[S <: Shape, O <: Shape] = Flatten[S]{type Out = O}
//  implicit def ss[S1 <: Shape, S2 <: Shape, MO <: Shape,  O <: Shape](implicit f: Aux[S2, O], m: TypeLevelMerge[S1, O, MO, _]): Aux[Egg[S1]+: S2, MO] ={
//  }
//}







