package chess

abstract sealed case class File(val name:String, value:Int) {
  def minusOne = name match {
    case "b" => Some(a)
    case "c" => Some(b)
    case "d" => Some(c)
    case "e" => Some(d)
    case "f" => Some(e)
    case "g" => Some(f)
    case "h" => Some(g)
    case _ => None
  }

  def plusOne = name match {
    case "a" => Some(b)
    case "b" => Some(c)
    case "c" => Some(d)
    case "d" => Some(e)
    case "e" => Some(f)
    case "f" => Some(g)
    case "g" => Some(h)
    case _ => None
  }

}

object a extends File("a",1)
object b extends File("b",2)
object c extends File("c",3)
object d extends File("d",4)
object e extends File("e",5)
object f extends File("f",6)
object g extends File("g",7)
object h extends File("h",8)

/*
object File extends Enumeration {
  type FileType = Value
  val a, b, c, d, e, f, g, h = Value
}
*/