package chess

case class Bishop(var p: Position, c: Color) extends Piece("Bishop", c, p) {

  def possiblePositions(board: Board) : List[Position] = {
    val sameDiagonal = board.allPositionsOnDiagonal(position)
    foreachPosition(board, sameDiagonal, Position++) ::: foreachPosition(board, sameDiagonal, Position+-) ::: foreachPosition(board, sameDiagonal, Position-+) ::: foreachPosition(board, sameDiagonal, Position--)
  }

  private def foreachPosition(board: Board, list: List[Position], op: (Position) => Option[Position]) : List[Position] = {
    list.filter { i => board.isSlideable(position, i, op) && ( board.isPositionEmpty(i) || board.isPositionOccupied(i, Color.other(color) ) )}
  }

}