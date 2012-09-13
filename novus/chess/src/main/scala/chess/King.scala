package chess

case class King(var position: Position, color: Color) extends Piece {
  override def name = "King"

  def possiblePositions(board: Board) : List[Position] = {
    val positions = List(position.plusRank, position.minusRank, position.plusFile, position.minusFile, position.minusRankminusFile, position.minusRankplusFile, position.plusRankplusFile, position.plusRankminusFile).filter { X => !X.isEmpty }.map { Y => Y.get }

    positions.filter { X => board.isPositionEmpty(X) || board.isPositionOccupied(X, Color.other(color)) }
  }

}