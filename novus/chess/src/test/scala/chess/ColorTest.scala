package chess

import org.scalatest.FunSuite

class ColorTest extends FunSuite {

  test("black is black") {
    assert(black == Rook(Position(one,F), black).color)
  }
  test("game turn is white on beginning") {
    val posF3 = Position(three, F)
    val blackPawn = Pawn(posF3, black)

    val pieces = List(blackPawn)
    val board = Board(pieces)
    val game = Game(board)
    assert(game.turn === white)
  }
}