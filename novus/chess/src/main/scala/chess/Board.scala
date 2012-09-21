package chess


case class Board(pieces: List[Piece]) {

  def findKing( color: Color) = pieces.find{ p => p.getClass() == classOf[King] && p.color == color } match {
    case Some(king) => Option(king)
    case _ => None
  }

  def findKingPosition( color: Color) = findKing(color) match {
    case Some(king) => Option(king.position)
    case _ => None
  }

  def isPositionEmpty(positionOption: Option[Position]) = positionOption match {
    case Some(position) => pieces.find{ p => p.position == position }.isEmpty
    case _ => false
  }

  def isPositionOccupied(positionOption: Option[Position]) = !isPositionEmpty(positionOption)

  def isPositionEmpty(position: Position) = {
    pieces.find{ p => p.position == position }.isEmpty
  }

  def getPieceAtPosition(position: Position) = pieces.find{ p => p.position == position }


  def isPositionOccupied(position: Option[Position], color: Color): Boolean = position match {
    case Some(pos) => isPositionOccupied(pos, color)
    case _ => false
  }
  
  def isPositionOccupied(position: Position, color: Color) = getPieceAtPosition(position) match {
    case Some(piece) if piece.color == color => true
    case _ => false
  }
  
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

  private def allPositionsOnDiagonal1(position: Position): List[Position] = position++ match {
    case Some(pos) => List(pos) ::: allPositionsOnDiagonal1(pos)
    case _ => Nil
  } 

  private def allPositionsOnDiagonal2(position: Position): List[Position] = position-+ match {
    case Some(pos) => List(pos) ::: allPositionsOnDiagonal2(pos)
    case _ => Nil
  } 
  private def allPositionsOnDiagonal4(position: Position): List[Position] = position+- match {
    case Some(pos) => List(pos) ::: allPositionsOnDiagonal4(pos)
    case _ => Nil
  } 
  private def allPositionsOnDiagonal3(position: Position):List[Position] = position-- match {
    case Some(pos) => List(pos) ::: allPositionsOnDiagonal3(pos)
    case _ => Nil
  } 
  
  
  def allPositionsOnDiagonal(position: Position) = {
    List(position) ::: allPositionsOnDiagonal1(position) ::: allPositionsOnDiagonal2(position) ::: allPositionsOnDiagonal3(position) ::: allPositionsOnDiagonal4(position)

  }

  def allPositionsByRank(rank: Rank) = List(Position('A',rank), Position('B',rank), Position('C',rank), Position('D',rank),Position('E',rank),Position('F',rank),Position('G',rank),Position('H',rank))

  def allPositionsByFile(file: File) = List(Position(file,1), Position(file,2), Position(file,3), Position(file,4),Position(file,5),Position(file,6),Position(file,7),Position(file,8))
      
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