package chess

import org.scalatest.FunSuite

class RookTest extends FunSuite {

  test("possiblePositions") {
    val posC1 = Position(C, one)
    val posA1 = Position(A, one)
    val posA3 = Position(A, three)
    val whiteRook = Rook(posA1, white)
    val whiteRook2 = Rook(posC1, white)

    val pieces = List(whiteRook, whiteRook2, Rook(posA3, black))
    val board = Board(pieces)

    val positions = whiteRook.possiblePositions(board)
    //positions.sort((e1,e2) => (e1 < e2)).foreach ( e => println("x: " + e) )

    val expected = List(Position(A, two), Position(A,three), Position(B,one))
    //println("----")
    //expected.sort((e1,e2) => (e1 < e2)).foreach ( e => println("y: " + e) )
    
    assert(positions.sort((e1,e2) => (e1 < e2)) === expected.sort((e1,e2) => (e1 < e2)))

  }
}