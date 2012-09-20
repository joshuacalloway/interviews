package chess

case class Rook(var p: Position, c: Color) extends Piece("Rook", c, p) {
 
 
  def possiblePositions(board: Board) : List[Position] = {
    val sameRank = board.allPositionsByRank(position.rank)
    val sameFile = board.allPositionsByFile(position.file)

    def foreachPosition(board: Board, list: List[Position], op: (Position) => Option[Position]) = {
      list.filter { i => board.isSlideable(position, i, op) && ( board.isPositionEmpty(i) || board.isPositionOccupied(i, Color.other(color) ) )}
    }

    foreachPosition(board, sameRank, Position.plusFile) ::: foreachPosition(board, sameRank, Position.minusFile) ::: foreachPosition(board, sameFile, Position.plusRank) ::: foreachPosition(board, sameFile, Position.minusRank)

  }
}