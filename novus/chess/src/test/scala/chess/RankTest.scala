package chess


import org.scalatest.FunSuite

class RankTest extends FunSuite {

  test("rank plusOne") {
    assert(one.plusOne == Some(two))
    assert(three.plusOne == Some(four))
    assert(eight.plusOne == None)
  }

  test("rank minusOne") {
    assert(one.minusOne == None)
    assert(three.minusOne == Some(two))
    assert(eight.minusOne == Some(seven))
  }

}