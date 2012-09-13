package chess

case class Queen(var position: Position, color: Color) extends Piece {
  override def name = "Queen"

  def possiblePositions(board: Board) : List[Position] = {
    val sameDiagonal = board.allPositionsOnDiagonal(position)
    val sameRank = board.allPositionsByRank(position.rank)
    val sameFile = board.allPositionsByFile(position.file)

    foreachPosition(board, sameDiagonal, Position.plusRankplusFile) ::: foreachPosition(board, sameDiagonal, Position.plusRankminusFile) ::: foreachPosition(board, sameDiagonal, Position.minusRankplusFile) ::: foreachPosition(board, sameDiagonal, Position.minusRankminusFile) ::: foreachPosition(board, sameRank, Position.plusFile) ::: foreachPosition(board, sameRank, Position.minusFile) ::: foreachPosition(board, sameFile, Position.plusRank) ::: foreachPosition(board, sameFile, Position.minusRank)
  }

  private def foreachPosition(board: Board, list: List[Position], op: (Position) => Option[Position]) : List[Position] = {
    list.filter { i => board.isSlideable(position, i, op) && ( board.isPositionEmpty(i) || board.isPositionOccupied(i, Color.other(color) ) )}
  }

}