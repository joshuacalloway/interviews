
package chess


case class Game(board : Board, var turn: Color = white) {

  def nextTurn = turn = Color.other(turn)

  def isCheck = if (board.pieces.filter { X => X.color == Color.other(turn) }.map { Y => Y.possiblePositions(board) }.flatten.filter { Z => Z == board.findKingPosition( turn ).get }.size > 0) true else false 
 
  def isCheckMate = isCheck && (if (board.findKing(turn).get.possiblePositions(board).intersect( board.pieces.filter { X => X.color == Color.other(turn) }.map { Y => Y.possiblePositions(board) }.flatten ).size > 0) true else false)
}
