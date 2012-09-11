package chess

case class Rook(start: Position, color: Color.ColorType) extends Piece {
  def possiblePositions(start: Position, board: Board) : List[Position] = start match {
//    case( Position(File.FileType.a, Rank.RankType.1, _)) 
    case _ => return Nil

  }
  def position : Position = {
    start
  }

}