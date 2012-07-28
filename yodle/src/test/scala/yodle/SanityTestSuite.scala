package yodle

import org.scalatest.FunSuite
import yodle.tri._
import yodle.juggle._
import collection.JavaConversions._
import scala.collection.JavaConversions._
import scala.collection.mutable.Map;


class SanityTestSuite extends FunSuite {

  // Triangle
  test("Test the simplest triangle of [1, 9, 2].  Should be 10") {
    val c = ComputeTriangleMax("/simpletriangle.txt")
    assert(c.computeMax == 10)
  }

  test("Test small triangle example from Yodle.  Should be 27") {
    val c = ComputeTriangleMax("/smalltriangle.txt")
    assert(c.computeMax == 27)
  }

  test("Test medium depth triangle of 18 levels.  Should be 171,000") {
    val c = ComputeTriangleMax("/mediumtriangle.txt")
    assert(c.computeMax == 171000)
  }

  test("Test the actual Yodle triangle.  Not sure what the answer is") {
    val c = ComputeTriangleMax("/triangle.txt")
    assert(c.computeMax == 732506)
  }


  // Juggle
  test("Simple . product of Circuit n Juggler") {
    val product = Circuit("C0", 1, 1, 1) * Juggler("J0", 1, 2, 3, List("C0"))
    assert(product == 6)
  }
  test("Simple . product of Juggler n Circuit") {
    val product = Juggler("J0", 1, 2, 3, List("C0")) * Circuit("C0", 2, 2, 2)
    assert(product == 12)
  }
  test("Juggler preferences with fit") {
    val circuitsIndexed = Map[String,Circuit]()
    circuitsIndexed("C0") = Circuit("C0", 2, 2, 2)
    circuitsIndexed("C1") = Circuit("C1", 1, 1, 1)
    val juggler = Juggler("J0", 1, 2, 3, List("C1", "C0"))
    val pref = juggler.preferencesWithFit(circuitsIndexed)
    assert( pref == List((circuitsIndexed("C1"),6 ),(circuitsIndexed("C0"),12 )  ))

  }

}
               
