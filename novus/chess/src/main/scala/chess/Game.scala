
package chess


case class Game(board : Board, var turn: Color = white) {

  def nextTurn = turn = Color.other(turn)

  def move(p: Piece, newPos:Position) = {
    p.position = newPos
    this
  }
  def undo(p: Piece, oldPos:Position) = {
    p.position = oldPos
  }

  def isCheck2 = if (board.pieces.filter { X => X.color == Color.other(turn) }.map { Y => Y.possiblePositions(board) }.flatten.filter { Z => Z == board.findKingPosition( turn ).get }.size > 0) true else false 

  def isCheck = {
    //printf("King is at " + board.findKingPosition( turn ))
    if (board.pieces.filter { X => X.color == Color.other(turn) }.map { Y => Y.possiblePositions(board) }.flatten.filter { Z => Z == board.findKingPosition( turn ).get }.size > 0) true else false 
  }

  def helperCheckMate2(piece: Piece, positions: List[Position]):Boolean = (piece.position, positions) match {
    case (_, Nil) => true
    case (oldPos, head :: tail) if (!move(piece, head).isCheck) => {
      move(piece,oldPos)
      false
    }
    case (oldPos, head :: tail) => {
      move(piece, oldPos)
      helperCheckMate2(piece, tail)
    }
  }

  def helperCheckMate(pieces: List[Piece]):Boolean = pieces match {
    case Nil => true
    case head :: tail if (!helperCheckMate2(head, head.possiblePositions(board))) => false
    case head :: tail => helperCheckMate(tail)
  }

  def isCheckMate = {
    if (!isCheck) false
    val pieces = board.pieces.filter { X => X.color == turn }
    helperCheckMate(pieces)
  }
}
