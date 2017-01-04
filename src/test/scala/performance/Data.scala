package performance

import scala.util.Random.nextInt

object Data {
  trait A
  trait B
  trait C
  trait D
  trait E
  trait F
  trait G
  trait H
  trait I
  trait J
  trait K
  trait L
  trait M

  def genIndicies = Iterator.continually(nextInt(14)).take(10000000).toList

  val indicies = genIndicies
}
