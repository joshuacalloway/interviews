package chess


case class Board(var pieces: List[Piece]) {

  def findKing( color: Color) = pieces.find{ p => p.getClass() == classOf[King] && p.color == color } match {
    case Some(king) => Option(king)
    case _ => None
  }

  def findKingPosition( color: Color) = findKing(color) match {
    case Some(king) => Option(king.position)
    case _ => None
  }

  def isPositionEmpty(position: Position) = pieces.find{ p => p.position == position }.isEmpty

  def isPositionEmpty(positionOption: Option[Position]):Boolean = isPositionEmpty(positionOption.get)

  def isPositionOccupied(position: Position) = !isPositionEmpty(position)

  def isPositionOccupied(positionOption: Option[Position]) = !isPositionEmpty(positionOption)

  def getPieceAtPosition(position: Position) = pieces.find{ p => p.position == position }

  def isPositionOccupied(position: Position, color: Color) = getPieceAtPosition(position) match {
    case Some(piece) if piece.color == color => true
    case _ => false
  }

 def isPositionOccupied(position: Option[Position], color: Color): Boolean = isPositionOccupied(position.getOrElse(null), color)
  
  // for Knights
  def jumpToPosition(start: Position, steps: List[ (Position) => Option[Position] ]): Option[Position] = steps match {
    case Nil => Some(start)
    case head :: tail if !head(start).isEmpty => jumpToPosition(head(start).get, tail)
    case _ => None
  }

                                                                                      // for Rooks, Bishops, Queen
  def isSlideable(start: Position, end: Position, movement: (Position) => Option[Position] ): Boolean = movement(start) match {
    case Some(otherPosition) if otherPosition == end => true
    case Some(otherPosition) if isPositionEmpty(otherPosition) => isSlideable(otherPosition, end, movement)
    case _ => false
  }

  private def allPositionsOnDiagonal(position:Position, func: (Position) => Option[Position] ):List[Position] = func(position) match {
    case None => Nil
    case Some(pos) => List(pos) ::: allPositionsOnDiagonal(pos, func)
  }
  
  def allPositionsOnDiagonal(position: Position):List[Position] = {
    List(position) ::: allPositionsOnDiagonal(position, Position++) ::: allPositionsOnDiagonal(position, Position+-) ::: allPositionsOnDiagonal(position, Position-+) ::: allPositionsOnDiagonal(position, Position--)
  }

  def allPositionsByRank(rank: Rank) = List(Position(A,rank), Position(B,rank), Position(C,rank), Position(D,rank),Position(E,rank),Position(F,rank),Position(G,rank),Position(H,rank))

  def allPositionsByFile(file: File) = List(Position(one,file), Position(two,file), Position(three,file), Position(four,file),Position(five,file),Position(six,file),Position(seven,file),Position(eight,file))
}

object Board {
  def createInitial = {
      val pieces = List[Piece](
			       Rook(Position(A,1),white),
			       Rook(Position(H,1),white),
                               Knight(Position(B,1),white),
                               Bishop(Position(C,1),white),
                               Queen(Position(D,1),white),
                               King(Position(E,1),white),
                               Bishop(Position(F,1),white),
                               Knight(Position(G,1),white),
                               Rook(Position(H,1),white),
                               Pawn(Position(A,2),white),
                               Pawn(Position(B,2),white),
                               Pawn(Position(C,2),white),
                               Pawn(Position(D,2),white),
                               Pawn(Position(E,2),white),
                               Pawn(Position(F,2),white),
                               Pawn(Position(G,2),white),
                               Pawn(Position(H,2),white),
			       Rook(Position(A,8),black),
			       Rook(Position(H,8),black),
                               Knight(Position(B,8),black),
                               Bishop(Position(C,8),black),
                               Queen(Position(D,8),black),
                               King(Position(E,8),black),
                               Bishop(Position(F,8),black),
                               Knight(Position(G,8),black),
                               Rook(Position(H,8),black),
                               Pawn(Position(A,7),black),
                               Pawn(Position(B,7),black),
                               Pawn(Position(C,7),black),
                               Pawn(Position(D,7),black),
                               Pawn(Position(E,7),black),
                               Pawn(Position(F,7),black),
                               Pawn(Position(G,7),black),
                               Pawn(Position(H,7),black)
      )
      new Board(pieces) 
  }
}