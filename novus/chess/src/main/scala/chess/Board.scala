package chess


case class Board(pieces: List[Piece]) {

  def isPositionEmpty(position: Position) : Boolean = {
    return true;
  }
}

object Board {

  def createInitial = {
      val pieces = List[Piece](
			       Rook(Position('a',1),white)
      )
  }
}