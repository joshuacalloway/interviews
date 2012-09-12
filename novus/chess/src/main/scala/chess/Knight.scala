package chess

case class Knight(var position: Position, color: Color) extends Piece {

  def possiblePositions(board: Board) : List[Position] = {
//    if (board.isPositionEmpty(position, List( Position.plusFile, Position.plusFile, Position.plusRank))) List(board.emptyPositions(position, List( Position.plusFile, Position.plusFile, Position.plusRank))) else Nil
    Nil
  }
}