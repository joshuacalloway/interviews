package chess

case class Rook(var position: Position, color: Color) extends Piece {
  def possiblePositions(board: Board) : List[Position] = { 
    board.possiblePositions(position, Position.plusRank, color) ::: 
    board.possiblePositions(position, Position.minusRank, color) ::: 
    board.possiblePositions(position, Position.plusFile, color) ::: 
    board.possiblePositions(position, Position.minusFile, color)
  }

}