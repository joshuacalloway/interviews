package chess


//case class Board(pieces: List[Piece], positions: List[Position]) {
case class Board(pieces: List[Piece]) {

  def isPositionEmpty(position: Position) = {
    pieces.find{ p => p.position == position }.isEmpty
  }
  def isPositionEmpty(positon: Position, steps: List[ (Position) => Option[Position] ]): Boolean = {
    false
  }
  def getPosition(positon: Position, steps: List[ (Position) => Option[Position] ]): Position = {
    Position(a, one)
  }

  def emptyAdjacentPositions(position: Position, operation: (Position) => Option[Position]): List[Position] = operation(position) match {
    case None => return Nil
    case Some(adjacent) if isPositionEmpty(adjacent) => List(adjacent) ::: emptyAdjacentPositions(adjacent, operation)
    case _ => return Nil
  }
}

object Board {


  def createEmptyBoard = {
  
  }

  def createInitial = {
      val pieces = List[Piece](
			       Rook(Position('a',1),white),
			       Rook(Position('h',1),white)

      )
  }
}