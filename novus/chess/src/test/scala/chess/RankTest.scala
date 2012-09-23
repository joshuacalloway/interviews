package chess


import org.scalatest.FunSuite

class RankTest extends FunSuite {

  test("rank++") {
    assert(Some(two) === one.++)
    assert(Some(four) === three.++)
    assert(None === eight.++)
  }

  test("rank--") {
    assert(None === one.--)
    assert(Some(two) === three.--)
    assert(Some(seven) === eight.--)
  }

}