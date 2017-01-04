package util.typelevel

import scala.annotation.implicitNotFound

object BoolOps {

  @implicitNotFound("type class extists for type ${T}")
  trait NOT[T]
  trait lo1 {
    implicit def no[T]: NOT[T] = null
  }

  object NOT extends lo1 {
    implicit def yes[T](implicit ev: T): NOT[T] = null
    implicit def yesAmbig[T](implicit ev: T): NOT[T] = null
  }

  @implicitNotFound("neither of the following types is available in implicit scope: ${T1} ,\n${T2}")
  trait OR[T1, T2] {
    type R
    def instance: T1 Either T2
  }
  trait LowOr {
    implicit def right[T1, T2](implicit i: T2): OR[T1, T2] = new OR[T1, T2] {
      type R = T2
      def instance = Right(i)
    }
  }
  object OR extends LowOr {
    type Aux[L, R, O] = OR[L, R] {type R = O}
    implicit def left[T1, T2](implicit i: T1): OR[T1, T2] = new OR[T1, T2] {
      type R = T1
      def instance = Left(i)
    }
  }

  @implicitNotFound("could not find both ${T1} and ${T2} in implicit scope")
  trait AND[T1, T2] {
    def left: T1
    def right: T2
  }
  object AND {
    implicit def both[T1, T2](implicit t1: T1, t2: T2): AND[T1, T2] = new AND[T1, T2] {
      def left: T1 = t1
      def right: T2 = t2
    }
  }


  trait IF[Cond, T, F] {
    type Out
    def apply(t: => T, f: => F): Out
  }
  trait lo { self: IF.type  =>
    implicit def _false[C, T, F]: IF.Aux[C, T, F, F] = falseInst.asInstanceOf[IF.Aux[C, T, F, F]]
  }
  object IF extends lo {
    type Aux[C, T, F, O] = IF[C, T, F] {type Out = O}

    val trueInst =  new IF[Any, Any, Any] {
      type Out = Any
      def apply(t: => Any, f: => Any): Out = t
    }

    val falseInst =  new IF[Any, Any, Any] {
      type Out = Any
      def apply(l: => Any, r: => Any): Out = r
    }
    implicit def _true[C, T, F](implicit c: C): IF.Aux[C, T, F, T] = trueInst.asInstanceOf[IF.Aux[C, T, F, T]]

  }

}
