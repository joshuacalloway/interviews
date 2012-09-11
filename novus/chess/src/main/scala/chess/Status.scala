package chess

object Status extends Enumeration {
  type StatusType = Value
  val Pawn, Rook, Knight, Bishop, Queen, King = Value
}