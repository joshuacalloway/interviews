package chess

import org.scalatest.FunSuite

class PositionTest extends FunSuite {

  test("position !=") {
    val p1 = Position(A, one)
    val p2 = Position(A, two)
    assert(p1 != p2)
  }

  test("position ==") {
    val p1 = Position(A, one)
    val p2 = Position(A, one)
    assert(p1 == p1)
  }

  test("position plusRank") {
    val posA1 = Position(A, one)
    val posA2 = Position(A, two)
    assert(posA1.plusRank.get == posA2)
  }

  test("position plusRank doesn't exist") {
    val p8 = Position(A, eight)
    assert(p8.plusRank == None)
  }

}