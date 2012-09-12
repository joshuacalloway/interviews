package chess

abstract sealed class Color(val name:String)

case object white extends Color("white")
case object black extends Color("black")


object Color {
  def other(color: Color) = if (color == white) black else white
}
