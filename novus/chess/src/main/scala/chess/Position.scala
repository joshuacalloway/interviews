
package chess

// http://en.wikipedia.org/wiki/Chessboard

case class Position(val file: File, val rank: Rank) {

  def color = if ((file.value + rank.value) % 2 == 0 ) black else white

  override def toString = "Position("+file.name+","+rank.name+")"

  def < (other: Position) = if (2*other.rank.value + other.file.value > 2*rank.value + file.value) true else false

  def adjacent(other: Position):Boolean = {
    if (other == this.plusRank.get) true 
    else if (other == this.minusRank.get) true
    else if (other == this.plusFile.get) true
    else if (other == this.minusFile.get) true
    else if (other == this.plusRankplusFile.get) true
    else if (other == this.plusRankminusFile.get) true
    else if (other == this.minusRankplusFile.get) true
    else if (other == this.minusRankminusFile.get) true
    else false
  }
/*
    if ((other.rank == rank.plusOne || other.rank == rank.minusOne) &&
       (other.file == file || other.file == file.plusOne || other.file == file.minusOne)) return true

    if ((other.file == file.plusOne || other.file == file.minusOne) &&
       (other.rank == rank || other.rank == rank.plusOne || other.rank == rank.minusOne)) return true
    false
  }
*/
  def plusRank: Option[Position] = rank.plusOne match {
    case Some(r) => Some(new Position(file, r))
    case _ => None
  }
  def minusRank: Option[Position] = rank.minusOne match {
    case Some(r) => Some(new Position(file, r))
    case _ => None
  } 
  def plusFile: Option[Position] = file.plusOne match {
    case Some(f) => Some(new Position(f, rank))
    case _ => None
  }
  def minusFile: Option[Position] = file.minusOne match {
    case Some(f) => Some(new Position(f, rank))
    case _ => None
  } 

  def plusRankplusFile: Option[Position] = (rank.plusOne, file.plusOne) match {
    case (Some(r), Some(f)) => Some(new Position(f, r))
    case _ => None
  }

  def plusRankminusFile: Option[Position] = (rank.plusOne, file.minusOne) match {
    case (Some(r), Some(f)) => Some(new Position(f, r))
    case _ => None
  }

  def minusRankplusFile: Option[Position] = (rank.minusOne, file.plusOne) match {
    case (Some(r), Some(f)) => Some(new Position(f, r))
    case _ => None
  }
  def minusRankminusFile: Option[Position] = (rank.minusOne, file.minusOne) match {
    case (Some(r), Some(f)) => Some(new Position(f, r))
    case _ => None
  }

 
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

  def plusFile(position: Position): Option[Position] = position.plusFile
  def minusFile(position: Position): Option[Position] = position.minusFile
  def plusRank(position: Position): Option[Position] = position.plusRank
  def minusRank(position: Position): Option[Position] = position.minusRank

  def minusRankminusFile(position: Position): Option[Position] = position.minusRankminusFile
  def minusRankplusFile(position: Position): Option[Position] = position.minusRankplusFile

  def plusRankminusFile(position: Position): Option[Position] = position.plusRankminusFile
  def plusRankplusFile(position: Position): Option[Position] = position.plusRankplusFile

}