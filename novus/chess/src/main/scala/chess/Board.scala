package chess


//case class Board(pieces: List[Piece], positions: List[Position]) {
case class Board(pieces: List[Piece]) {

  def getPieceAtPosition(position: Position) = pieces.find{ p => p.position == position }

  def isPositionEmpty(positionOption: Option[Position]) = positionOption match {
    case Some(position) => pieces.find{ p => p.position == position }.isEmpty
    case _ => false
  }

  def isPositionEmpty(position: Position) = {
    pieces.find{ p => p.position == position }.isEmpty
  }
  def isPositionEmpty(positon: Position, steps: List[ (Position) => Option[Position] ]): Boolean = {
    false
  }
  def emptyPositions(position: Position, steps: List[ (Position) => Option[Position] ]): List[Position] = steps match {
    case Nil => Nil
    case head :: tail if isPositionEmpty(head(position)) => List(head(position).get) ::: emptyPositions(head(position).get, steps)
  }

  def emptyPositions(position: Position, operation: (Position) => Option[Position]): List[Position] = operation(position) match {
    case None => return Nil
    case Some(adjacent) if isPositionEmpty(adjacent) => List(adjacent) ::: emptyPositions(adjacent, operation)
    case _ => return Nil
  }

  def possiblePositions(position: Position, operation: (Position) => Option[Position], color: Color): List[Position] = operation(position) match {
    case None => return Nil
    case Some(adjacent) if isPositionEmpty(adjacent) => List(adjacent) ::: possiblePositions(adjacent, operation, color)
    case Some(adjacent) if getPieceAtPosition(adjacent).get.color != color => List(adjacent) 
    case _ => Nil
  }

}

object Board {


  def createEmptyBoard = {
  
  }

  def createInitial = {
      val pieces = List[Piece](
			       Rook(Position('A',1),white),
			       Rook(Position('H',1),white)

      )
  }
}