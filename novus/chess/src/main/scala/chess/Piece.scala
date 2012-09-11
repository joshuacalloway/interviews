package chess

abstract class Piece extends Moveable {
  def color : Color.ColorType
  def position : Position
//  def status : Status.StatusType
}