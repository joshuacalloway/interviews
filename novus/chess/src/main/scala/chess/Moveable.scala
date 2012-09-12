package chess

trait Moveable {

  def possiblePositions(board: Board) : List[Position]      
}