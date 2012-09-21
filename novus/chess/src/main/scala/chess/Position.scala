
package chess

// http://en.wikipedia.org/wiki/Chessboard

case class Position(val file: File, val rank: Rank) {

  def color = if ((file.value + rank.value) % 2 == 0 ) black else white

  override def toString = "Position("+file.name+","+rank.name+")"

  def < (other: Position) = if (2*other.rank.value + other.file.value > 2*rank.value + file.value) true else false
  def + (other: Position) = Position(this.file + other.file, this.rank + other.rank)
  def - (other: Position) = Position(this.file - other.file, this.rank - other.rank)

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
  def *+() = if (this.rank.value + 1 <= Rank.MAX) Some(Position(file, Rank(this.rank.value + 1))) else None
  def *-() = if (this.rank.value - 1 >= Rank.MIN) Some(Position(file, Rank(this.rank.value - 1))) else None

  def +*() = if (this.file.value + 1 <= File.MAX) Some(Position(File(this.file.value + 1), rank)) else None
  def -*() = if (this.file.value - 1 >= File.MIN) Some(Position(File(this.file.value - 1), rank)) else None

  def ++() = if (this.rank.value + 1 <= Rank.MAX && this.file.value + 1 <= File.MAX) Some(Position(File(this.file.value + 1),Rank(this.rank.value + 1))) else None
  def +-() = if (this.file.value + 1 <= File.MAX && this.rank.value - 1 >= Rank.MIN) Some(Position(File(this.file.value + 1),Rank(this.rank.value - 1))) else None

  def -+() = if (this.file.value - 1 >= File.MIN && this.rank.value + 1 <= Rank.MAX) Some(Position(File(this.file.value - 1),Rank(this.rank.value + 1))) else None
  def --() = if (this.rank.value - 1 >= Rank.MIN && this.file.value - 1 >= File.MIN) Some(Position(File(this.file.value - 1),Rank(this.rank.value - 1))) else None
}

object Position {
  def apply(file: File, rank: Option[Rank]):Position = rank match {
    case Some(r) => Position(file, r)
    case _ => throw new IllegalArgumentException("Position not creatable")
  }

  def apply(file: Option[File], rank: Rank):Position = file match {
    case Some(f) => Position(f, rank)
    case _ => throw new IllegalArgumentException("Position with File not creatable")
  }

  def apply(file: Option[File], rank: Option[Rank]) :Position = (file, rank) match {
    case (Some(f), Some(r)) => Position(f, r)
    case _ => throw new IllegalArgumentException("Position with File not creatable")
  }

  def apply(file: Char, rank: Rank) = { 
    new Position(File(file),rank)
  }

  def apply(file: Char, rank: Int) = { 
    new Position(File(file),Rank(rank))
  }

  def apply(file: File, rank: Int) = { 
    new Position(file,Rank(rank))
  }

  def *+(position: Position) = position*+
  def *-(position: Position) = position*-
  def +*(position: Position) = position+*
  def -*(position: Position) = position-*

  def --(position: Position) = position--
  def -+(position: Position) = position-+
  def +-(position: Position) = position+-
  def ++(position: Position) = position++
}