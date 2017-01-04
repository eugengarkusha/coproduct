
package util.coproduct

import org.scalatest.FunSuite
import org.scalatest.Matchers
import shapeless.Nat
import util.coproduct.Coproduct.:+:
import util.coproduct.ops.Add
import util.coproduct.ops.IndexOf
import util.coproduct.ops.Remove
import util.coproduct.syntax

class OpsTest extends FunSuite with Matchers {

  test("basic ops") {

    val e: Coproduct[+:[String, +:[Boolean, +:[Int, Cnil]]]] = Add(99).to[String +: Boolean :+: Int]
    e shouldBe Coproduct(99, 2)

    val e1: Coproduct[+:[String, +:[Boolean, +:[Int, Cnil]]]] = Add(true).to[String +: Boolean :+: Int]
    e1 shouldBe Coproduct(true, 1)

    val e2: Coproduct[+:[Char, +:[String, :+:[Boolean, Int]]]] = Add('c').to[String +: Boolean :+: Int]
    e2 shouldBe Coproduct('c', 0)

    val e3: Coproduct[+:[String, +:[Boolean, +:[Int, Cnil]]]] = Add("str").to[String +: Boolean +: Int +: Cnil]
    e3 shouldBe Coproduct("str",0)

    val r: Either[Coproduct[+:[String, +:[Boolean, Cnil]]], Int] =  Remove[Int].from(e)
    r shouldBe Right(99)

    val r11: Either[Coproduct[+:[Boolean, +:[Int, Cnil]]], String] = Remove[String].from(e)
    r11 shouldBe Left(Coproduct(99, 1))

    val r12: Either[Coproduct[+:[Boolean, +:[Int, Cnil]]], String] = Remove[String].from(e3)
    r12 shouldBe Right("str")

    val r13: Either[Coproduct[+:[Char, +:[String, +:[Int, Cnil]]]], Boolean] = Remove[Boolean].from(e2)
    r13 shouldBe Left(Coproduct('c', 0))


    type a = Int :+: Boolean
    type b = String +: Int :+: Char
    val A: Coproduct[a] = Add(2).to[a]
    val B = Add('t').to[b]
    val C = Add(1).to[String +: Char +: Cnil]

    IndexOf[a, Int] shouldBe 1
    IndexOf[a, Boolean] shouldBe 2
    IndexOf[a, Char] shouldBe 0
    IndexOf[b, Nat] shouldBe 0
    IndexOf[b, Char] shouldBe 3



    val x: Coproduct[Int +: Boolean +: String :+: Char] = A.extendRightBy[b]
    x shouldBe(Coproduct(2, 0))
    val x1: Coproduct[+:[String, +:[Char, +:[Int, +:[Boolean, Cnil]]]]] = A.extendLeftBy[b]
    x1 shouldBe(Coproduct(2, 2))
    val x2: Coproduct[Boolean +: String +: Int :+: Char] = B.extendLeftBy[a]
    x2 shouldBe(Coproduct('t', 3))


    val e4 = Add(A).to[Int +: Coproduct[a] :+: Boolean]
    val e5 = Add(A).to[Byte +: Char +: Int +: Coproduct[a] :+: Boolean]
    val e6 = Add(A).to[Char +: Int +: Coproduct[a] +: Coproduct[Int :+: String] +: Coproduct[Int :+: Boolean] :+: Boolean]
    val e7 = Add(A).to[Coproduct[Exception +: Cnil] +: Char +: Coproduct[Byte +: a] +: Int +: Coproduct[a] +: Short +: Coproduct[Int :+: String] +: Coproduct[Int :+: Boolean] :+: Boolean]
    val e8 = Add(A).to[Char +: Coproduct[a] :+: Int]

    val r1: Coproduct[+:[Int, +:[Boolean, Cnil]]] = e4.flatten
    r1.i shouldBe  0

    val r2: Coproduct[+:[Byte, +:[Char, +:[Int, +:[Boolean, Cnil]]]]] = e5.flatten

    r2.i shouldBe 2

    val r3: Coproduct[+:[Char, +:[String, +:[Int, +:[Boolean, Cnil]]]]] = e6.flatten
    r3.i shouldBe 2

    val r4: Coproduct[+:[Exception, +:[Char, +:[Byte, +:[Short, +:[String, +:[Int, +:[Boolean, Cnil]]]]]]]] = e7.flatten
    r4.i shouldBe 5

    val r5: Coproduct[+:[Char, +:[Boolean, +:[Int, Cnil]]]] = e8.flatten
    r5.i shouldBe 2

    Remove[Int].from(r4) shouldBe  Right(2)

  }
}
