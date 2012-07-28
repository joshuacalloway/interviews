package yodle

import org.scalatest.FunSuite
import yodle.tri._
import collection.JavaConversions._
import scala.collection.JavaConversions._


class SanityTestSuite extends FunSuite {

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

}
               
