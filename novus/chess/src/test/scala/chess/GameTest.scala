package chess

import org.scalatest.FunSuite

class GameTest extends FunSuite {
  test("basic intersection of List[Position]") {
    val list1 = List( Position(C,1), Position(A,3))
    val list2 = List( Position(C,1), Position(A,5))
    assert(list1.intersect(list2) == List(Position(C,1)))
  }
  test("basic check of Black King") {
    val posD8 = Position(D,8)
    val blackKing = King(posD8, black)
    val whiteKing = King(Position(D,1), white)

    val whiteRook = Rook(Position(D,3), white)
    val board = Board(List(blackKing, whiteKing,whiteRook))
    val game = Game(board, black)
    assert(game.isCheck)
  }

  test("basic check mate of Black King") {
    val posD8 = Position(D,8)
    val blackKing = King(posD8, black)
    val whiteKing = King(Position(D,1), white)

    val whiteRook1 = Rook(Position(A,8), white)
    val whiteRook2 = Rook(Position(A,7), white)

    val board = Board(List(blackKing, whiteKing,whiteRook1, whiteRook2))
    val game = Game(board, black)
    assert(game.isCheckMate)
  }

  test("Check but not check mate of Black King") {
    val posD8 = Position(D,8)
    val blackKing = King(posD8, black)
    val whiteKing = King(Position(D,1), white)

    val whiteRook1 = Rook(Position(C,8), white)
    val whiteRook2 = Rook(Position(A,7), white)

    val board = Board(List(blackKing, whiteKing,whiteRook1, whiteRook2))
    val game = Game(board, black)
    assert(!game.isCheckMate)
  }
}