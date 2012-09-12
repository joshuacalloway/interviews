package chess

case class Knight(var position: Position, color: Color) extends Piece {

  def possiblePositions(board: Board) : List[Position] = {
//    if (true) board.emptyAdjacentPositions(position, Position.minusFile) else Nil 
  //  val movement1 = List( Position.plusFile, Position.plusFile, Position.plusRank)

    if (board.isPositionEmpty(position, List( Position.plusFile, Position.plusFile, Position.plusRank))) List(board.getPosition(position, List( Position.plusFile, Position.plusFile, Position.plusRank))) else Nil
  }
}