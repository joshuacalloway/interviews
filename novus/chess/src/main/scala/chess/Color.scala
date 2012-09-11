package chess

abstract sealed class Color(val name:String)

case object white extends Color("white")
case object black extends Color("black")

/*
object Color extends Enumeration {
  type ColorType = Value
  val white, black = Value
}
*/