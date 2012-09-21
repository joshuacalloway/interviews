package chess

abstract sealed case class File(val name:String, value:Int) {
  def +(other:File) = File(value+other.value)
  def -(other:File) = File(value-other.value)

  def -- = name match {
    case "B" => Some(A)
    case "C" => Some(B)
    case "D" => Some(C)
    case "E" => Some(D)
    case "F" => Some(E)
    case "G" => Some(F)
    case "H" => Some(G)
    case _ => None
  }

  def ++ = name match {
    case "A" => Some(B)
    case "B" => Some(C)
    case "C" => Some(D)
    case "D" => Some(E)
    case "E" => Some(F)
    case "F" => Some(G)
    case "G" => Some(H)
    case _ => None
  }

}

object A extends File("A",1)
object B extends File("B",2)
object C extends File("C",3)
object D extends File("D",4)
object E extends File("E",5)
object F extends File("F",6)
object G extends File("G",7)
object H extends File("H",8)

object NULL_FILE extends File("X", -1)
object File
{
  def MAX = 8
  def MIN = 1

  def apply(v: Int) = v match {
    case 1 => A
    case 2 => B
    case 3 => C
    case 4 => D
    case 5 => E
    case 6 => F
    case 7 => G
    case 8 => H    
    case 'A' => A
    case 'B' => B
    case 'C' => C
    case 'D' => D
    case 'E' => E
    case 'F' => F
    case 'G' => G
    case 'H' => H    
    case _ => NULL_FILE
  }
}