package chess

case class Bishop(var p: Position, c: Color) extends Piece("Bishop", c, p) {

  override def possiblePositions(board: Board) : List[Position] = {
    def foreachPosition(board: Board, list: List[Position], op: (Position) => Option[Position]) : List[Position] = {
      list.filter { i => board.isSlideable(position, i, op) && ( board.isPositionEmpty(i) || board.isPositionOccupied(i, Color.other(color) ) )}
    }

    val sameDiagonal = board.allPositionsOnDiagonal(position)
    foreachPosition(board, sameDiagonal, Position++) ::: foreachPosition(board, sameDiagonal, Position+-) ::: foreachPosition(board, sameDiagonal, Position-+) ::: foreachPosition(board, sameDiagonal, Position--)
  }


}