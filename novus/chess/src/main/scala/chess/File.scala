package chess

abstract sealed class File(val name:String)

case object a extends File("a")
case object b extends File("b")
case object c extends File("c")
case object d extends File("d")
case object e extends File("e")
case object f extends File("f")
case object g extends File("g")
case object h extends File("h")

/*
object File extends Enumeration {
  type FileType = Value
  val a, b, c, d, e, f, g, h = Value
}
*/