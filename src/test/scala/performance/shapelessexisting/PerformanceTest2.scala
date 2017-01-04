package performance.shapelessexisting

import org.scalatest.FunSuite
import org.scalatest.Matchers
import performance.Data._
import shapeless._

class PerformanceTest2 extends FunSuite with Matchers {

  val funcs: Map[Int, (Int) => Coproduct] =
    (((i: Int) => Coproduct[Int :+: A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: Int :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: Int :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: Int :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: Int :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: Int :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: Int :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: Int :+: H :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: Int :+: I :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: Int :+: J :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: Int :+: K :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: Int :+: L :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: Int :+: M :+: CNil](i)) ::
      ((i: Int) => Coproduct[A :+: B :+: C :+: D :+: E :+: F :+: G :+: H :+: I :+: J :+: K :+: L :+: M :+: Int :+: CNil](i)) :: Nil).zipWithIndex.map(_.swap).toMap

  //warmup
  println(genIndicies.map(i => funcs(i)(i)).last)
  println(genIndicies.map(i => funcs(i)(i)).last)
  println(genIndicies.map(i => funcs(i)(i)).last)

  test("additions (shapeless)") {
    val start = System.nanoTime()
    println(indicies.map(i => funcs(i)(i)).last)
    val end = System.nanoTime()
    println("elapsed =" + (end - start))

  }
}
