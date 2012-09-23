package chess

import org.scalatest.FunSuite

class BoardTest extends FunSuite {
  test("Find black King") {
    val posC8 = Position(C,8)
    val blackKing = King(posC8, black)
    val whiteKing = King(Position(D,1), white)
    val pieces = List(blackKing, whiteKing)
    val board = Board(pieces)
    assert(posC8 === board.findKingPosition(black).get)
  }
}