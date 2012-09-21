package chess

case class Knight(var p: Position, c: Color) extends Piece("Knight", c, p) {

  def possiblePositions(board: Board) = {
    val positions = List(
      board.jumpToPosition(position, List( Position+*, Position+*, Position*+)), 
      board.jumpToPosition(position, List( Position+*, Position+*, Position*-)), 
      board.jumpToPosition(position, List( Position-*, Position-*, Position*+)), 
      board.jumpToPosition(position, List( Position-*, Position-*, Position*-)), 
      board.jumpToPosition(position, List( Position*+, Position*+, Position+*)),
      board.jumpToPosition(position, List( Position*+, Position*+, Position-*)),
      board.jumpToPosition(position, List( Position*-, Position*-, Position+*)),
      board.jumpToPosition(position, List( Position*-, Position*-, Position-*)))
    positions.filter { X => !X.isEmpty }.map { Y => Y.get }.filter { Z => !board.isPositionOccupied(Z, color) }
  }
}