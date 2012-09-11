package chess


case class Board(pieces: List[Piece]) {

  def isPositionEmpty(position: Position) : Boolean = {
    return true;
  }
}

object Board {

  def createInitial = {
    
  }
}