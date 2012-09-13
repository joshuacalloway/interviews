package chess

abstract class Piece extends Moveable {
  def name: String
  def color : Color
  def position : Position
}