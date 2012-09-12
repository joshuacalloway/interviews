package chess

case class Rook(var position: Position, color: Color) extends Piece {

  def possiblePositions2(board: Board) : List[Position] = { 
    board.emptyAdjacentPositions(position, Position.plusRank) ::: 
    board.emptyAdjacentPositions(position, Position.minusRank) ::: 
    board.emptyAdjacentPositions(position, Position.plusFile) ::: 
    board.emptyAdjacentPositions(position, Position.minusFile)
  }

  def possiblePositions(board: Board) : List[Position] = { 
    board.possiblePositions(position, Position.plusRank, color) ::: 
    board.possiblePositions(position, Position.minusRank, color) ::: 
    board.possiblePositions(position, Position.plusFile, color) ::: 
    board.possiblePositions(position, Position.minusFile, color)
  }

}