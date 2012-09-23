package chess

case class King(var p: Position, c: Color) extends Piece("King", c, p) {

  override def possiblePositions(board: Board) = {
    val positions = List(position+*, position-*, position*+, position*-, position--, position-+, position++, position+-).filter { X => !X.isEmpty }.map { Y => Y.get }

    positions.filter { X => board.isPositionEmpty(X) || board.isPositionOccupied(X, Color.other(color)) }
  }

}