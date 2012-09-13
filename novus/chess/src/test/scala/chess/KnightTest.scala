package chess

import org.scalatest.FunSuite

class KnightTest extends FunSuite {

  test("Knight on C3 can move jump around") {
    val posC3 = Position(C, three)
    val posC4 = Position(C, four)
    val posD5 = Position(D, five)   
    val posD1 = Position(D, one)   
    val posE2 = Position(E, two)   

    val whiteKnight = Knight(posC3, white)
    val whiteBishop = Bishop(posC4, white)
    val blackPawn = Pawn(posD5, black)
    val whitePawn = Pawn(posE2, white)

    val whiteQueen = Queen(posD1, white)

    val pieces = List(whiteKnight, whiteBishop, blackPawn, whitePawn, whiteQueen)
    val board = Board(pieces)

    val positions = whiteKnight.possiblePositions(board)
   // positions.sort((e1,e2) => (e1 < e2)).foreach ( e => println("x: " + e) )

    val expected = List(Position(D, five), Position(B,five), Position(B,one), Position(A,2), Position(A,4), Position(E,4))
    //println("----")
    //expected.sort((e1,e2) => (e1 < e2)).foreach ( e => println("y: " + e) )
    
    assert(positions.sort((e1,e2) => (e1 < e2)) == expected.sort((e1,e2) => (e1 < e2)))

  }
}