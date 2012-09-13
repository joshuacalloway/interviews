package chess

import org.scalatest.FunSuite

class PawnTest extends FunSuite {

  test("possiblePositions for White Pawn on E2") {
    val posE2 = Position(E, two)
    val posF3 = Position(F, three)
    val posD3 = Position(D, three)
    val whitePawn = Pawn(posE2, white)
    val whitePawn2 = Pawn(posD3, white)
    val blackPawn = Pawn(posF3, black)

    val pieces = List(whitePawn, whitePawn2, blackPawn)
    val board = Board(pieces)

    val positions = whitePawn.possiblePositions(board)
    val expected = List(Position(E, three), Position(E,four), Position(F,three))
        
    assert(positions.sort((e1,e2) => (e1 < e2)) == expected.sort((e1,e2) => (e1 < e2)))

  }
}