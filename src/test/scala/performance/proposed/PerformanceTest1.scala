package performance.proposed

import org.scalatest.FunSuite
import org.scalatest.Matchers
import performance.Data
import util.coproduct.+:
import util.coproduct.Coproduct
import util.coproduct.Coproduct.:+:
import util.coproduct.ops.Add

class PerformanceTest1 extends FunSuite with Matchers {

  import Data._

  val funcs: Map[Int, (Int) => Coproduct[_]] =
    (((i: Int) => Add(i).to[Int +: A +: B +: C +: D +: E +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: Int +: B +: C +: D +: E +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: Int +: C +: D +: E +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: Int +: D +: E +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: Int +: E +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: Int +: F +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: Int +: G +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: Int +: H +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: Int +: I +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: I +: Int +: J +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: I +: J +: Int +: K +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: I +: J +: K +: Int +: L :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: I +: J +: K +: L +: Int :+: M]) ::
      ((i: Int) => Add(i).to[A +: B +: C +: D +: E +: F +: G +: H +: I +: J +: K +: L +: M :+: Int]) :: Nil).zipWithIndex.map(_.swap).toMap

  //warmup
  println(genIndicies.map(i => funcs(i)(i)).last)
  println(genIndicies.map(i => funcs(i)(i)).last)
  println(genIndicies.map(i => funcs(i)(i)).last)

  test("additions (proposed impl)") {
    val start = System.nanoTime()
    println(indicies.map(i => funcs(i)(i)).last)
    val end = System.nanoTime()
    println("elapsed =" + (end - start))
  }

}
