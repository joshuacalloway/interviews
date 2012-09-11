package chess

case class Rook(var position: Position, color: Color) extends Piece {
  def possiblePositions(start: Position, board: Board) : List[Position] = start match {
//    case( Position(File.FileType.a, Rank.RankType.1, _)) 
    case _ => return Nil

  }
}