package chess

trait Moveable {

  def possiblePositions(start: Position, board: Board) : List[Position]      
}