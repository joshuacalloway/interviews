package chess

case class Rook(var position: Position, color: Color) extends Piece {

  def possiblePositions(board: Board) : List[Position] = board.emptyAdjacentPositions(position, Position.plusRank) ::: board.emptyAdjacentPositions(position, Position.minusRank) ::: board.emptyAdjacentPositions(position, Position.plusFile) ::: board.emptyAdjacentPositions(position, Position.minusFile)


}