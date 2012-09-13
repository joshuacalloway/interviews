package chess

case class Knight(var position: Position, color: Color) extends Piece {
  override def name = "Knight"

  def possiblePositions(board: Board) : List[Position] = {
    val positions = List(
      board.jumpToPosition(position, List( Position.plusRank, Position.plusRank, Position.plusFile)), 
      board.jumpToPosition(position, List( Position.plusRank, Position.plusRank, Position.minusFile)), 
      board.jumpToPosition(position, List( Position.minusRank, Position.minusRank, Position.plusFile)), 
      board.jumpToPosition(position, List( Position.minusRank, Position.minusRank, Position.minusFile)), 
      board.jumpToPosition(position, List( Position.plusFile, Position.plusFile, Position.plusRank)),
      board.jumpToPosition(position, List( Position.plusFile, Position.plusFile, Position.minusRank)),
      board.jumpToPosition(position, List( Position.minusFile, Position.minusFile, Position.plusRank)),
      board.jumpToPosition(position, List( Position.minusFile, Position.minusFile, Position.minusRank)))
    positions.filter { X => !X.isEmpty }.map { Y => Y.get }.filter { Z => !board.isPositionOccupied(Z, color) }
  }
}