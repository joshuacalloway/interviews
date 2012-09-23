
package chess

// http://en.wikipedia.org/wiki/Chessboard

case class Position(rank: Rank, file: File) extends Ordered[Position] {
  def color = if ((file.value + rank.value) % 2 == 0 ) black else white
  //override def toString = "Position("+file.name+","+rank.name+")"
  def value = 2*rank.value + file.value

  override def compare(that: Position) = value - that.value
  
  def - (other: Position) = Position(rank + other.rank, file + other.file)
  def + (other: Position) = Position(rank - other.rank, file - other.file)

  def adjacent(other: Position):Boolean = {
    if (Some(other) == (this).+*) true 
    else if (Some(other) == (this).-*) true
    else if (Some(other) == (this).*+) true
    else if (Some(other) == (this).*-) true
    else if (Some(other) == (this).++) true
    else if (Some(other) == (this).+-) true
    else if (Some(other) == (this).-+) true
    else if (Some(other) == (this).--) true
    else false
  }
  def +*() = Position(rank++, file)
  def -*() = Position(rank--, file)
  def *+() = Position(rank,file++)
  def *-() = Position(rank,file--)
  def ++() = Position(rank++,file++)
  def -+() = Position(rank--,file++)
  def +-() = Position(rank++,file--)
  def --() = Position(rank--,file--)
}

object Position {
  def apply(file: File, rank: Rank):Position = Position(rank,file)

  def apply(rank: Option[Rank], file:File):Option[Position] = rank match {
    case Some(r) => Some(Position(r, file))
    case _ => None
  }

  def apply(file: File, rank: Option[Rank]):Option[Position] = apply(rank,file)

  def apply(rank:Rank, file: Option[File]):Option[Position] = file match {
    case Some(f) => Some(Position(rank, f))
    case _ => None
  }

  def apply(file: Option[File], rank: Rank):Option[Position] = apply(rank,file)

  def apply(rank: Option[Rank], file: Option[File]) :Option[Position] = (rank, file) match {
    case (Some(r), Some(f)) => Some(Position(r,f))
    case _ => None
  }

  def apply(rank:Rank, file: Int):Position = apply(rank,File(file))
  def apply(file: Int, rank:Rank):Position = apply(rank,File(file))
  def apply(rank:Int, file:Int):Position = apply(Rank(rank),File(file))
  def apply(rank:Int, file:File):Position = apply(Rank(rank),file)
  def apply(file:File, rank:Int):Position = apply(rank,file)


  def *+(position: Position) = position*+
  def *-(position: Position) = position*-
  def +*(position: Position) = position+*
  def -*(position: Position) = position-*

  def --(position: Position) = position--
  def -+(position: Position) = position-+
  def +-(position: Position) = position+-
  def ++(position: Position) = position++
}