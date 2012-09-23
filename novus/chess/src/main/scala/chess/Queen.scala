package chess

case class Queen(var p: Position, c: Color) extends Piece("Queen", c, p) {

  override def possiblePositions(board: Board) : List[Position] = {
    val sameDiagonal = board.allPositionsOnDiagonal(position)
    val sameRank = board.allPositionsByRank(position.rank)
    val sameFile = board.allPositionsByFile(position.file)


    def foreachPosition(board: Board, list: List[Position], op: (Position) => Option[Position]) = {
      list.filter { i => board.isSlideable(position, i, op) && ( board.isPositionEmpty(i) || board.isPositionOccupied(i, Color.other(color) ) )}
    }

    foreachPosition(board, sameDiagonal, Position++) ::: foreachPosition(board, sameDiagonal, Position+-) ::: foreachPosition(board, sameDiagonal, Position-+) ::: foreachPosition(board, sameDiagonal, Position--) ::: foreachPosition(board, sameRank, Position*+) ::: foreachPosition(board, sameRank, Position*-) ::: foreachPosition(board, sameFile, Position+*) ::: foreachPosition(board, sameFile, Position-*)
  }


}