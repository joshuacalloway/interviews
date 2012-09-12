package chess

abstract class Piece extends Moveable {
  def color : Color
  def position : Position
}