package chess

import org.scalatest.FunSuite

class BishopTest extends FunSuite {

  test("white Bishop on white square can only move to white squares") {
    val posF1 = Position(F, 1)
    val bishop = Bishop(posF1, white)
    
    val pieces = List(bishop)
    val board = Board(pieces)

    val positions = bishop.possiblePositions(board)
    assert(positions.find { p => p.color == black }.isEmpty)
  }

  test("white Bishop on C1 can take black Bishop on H6") {
    val posC1 = Position(C, 1)
    val bishop = Bishop(posC1, white)
    val blackBishop = Bishop(Position(H,6), black)
    val pieces = List(bishop, blackBishop)
    val board = Board(pieces)

    val positions = bishop.possiblePositions(board)
    assert(!positions.find { p => p == Position(H,6) }.isEmpty)
  }

}