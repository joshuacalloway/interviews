package yodle

import org.scalatest.FunSuite

import collection.JavaConversions._
import scala.collection.JavaConversions._


class SanityTestSuite extends FunSuite {

  test("Simple Sanity Test for Triangle(String[]) max") {
    val simpleTri = Triangle(Array("5"))
    assert(5 == simpleTri.max())
  }
 
 test("Simple Sanity Test for Triangle(String) max") {
    val simpleTri = Triangle("5")
    assert(5 == simpleTri.max())
  }
 
  test("Simple Sanity Test for Triangle Max") {
    val simpleTri = Triangle(5)
    assert(5 == simpleTri.max())
  }
  test("Simple Sanity Test for another Triangle Max") {
    val simpleTri = Triangle(5, 2, 7)
    assert(12 == simpleTri.max())
  }

  test("Simple Sanity Test for another Triangle(Array[String]) Max") {
    val simpleTri = Triangle(Array("5", "2", "7"))
    assert(12 == simpleTri.max())
  }

  test("Simple Sanity Test for another Triangle(String,String,String) Max") {
    val simpleTri = Triangle("5", "2", "7")
    assert(12 == simpleTri.max())
  }

  test("Simple Sanity Test for more complicated Triangle Max") {
    val simpleTri = Triangle(5, Triangle(3), Triangle(1))
    assert(8 == simpleTri.max())
  }

  test("Simple Sanity Test for another complicated Triangle Max") {
    val simpleTri = Triangle(5, Triangle(3, 1, 20), Triangle(1, 20, 5))
    assert(28 == simpleTri.max())
  }

  test("Simple Sanity Test for another complicated Triangle(Array[String]) Max") {
    val simpleTri = Triangle(Array("5", "3", "1", "1", "20", "5"))
    assert(28 == simpleTri.max())
  }

  test("Sanity test with yodle small triangle(Array[String]) Max") {
    val tri = Triangle(Array("5", "9", "6", "4", "6", "8", "0", "7", "1", "5"))
    assert(27 == tri.max())
  }
}
               
