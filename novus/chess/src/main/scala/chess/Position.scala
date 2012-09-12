
package chess

// http://en.wikipedia.org/wiki/Chessboard

case class Position(file: File, rank: Rank) {
  override def toString = "Position("+file.name+","+rank.name+")"
  def < (other: Position) = if (other.rank.value + other.file.value > rank.value + file.value) true else false

  def adjacent(other: Position):Boolean = {
    if ((other.rank == rank.plusOne || other.rank == rank.minusOne) &&
       (other.file == file || other.file == file.plusOne || other.file == file.minusOne)) return true

    if ((other.file == file.plusOne || other.file == file.minusOne) &&
       (other.rank == rank || other.rank == rank.plusOne || other.rank == rank.minusOne)) return true
    false
  }

  def plusRank: Option[Position] = rank.plusOne match {
    case Some(x) => Some(new Position(file, rank.plusOne.get))
    case _ => None
  }
  def minusRank: Option[Position] = rank.minusOne match {
    case Some(x) => Some(new Position(file, rank.minusOne.get))
    case _ => None
  } 
  def plusFile: Option[Position] = file.plusOne match {
    case Some(x) => Some(new Position(file.plusOne.get, rank))
    case _ => None
  }
  def minusFile: Option[Position] = file.minusOne match {
    case Some(x) => Some(new Position(file.minusOne.get, rank))
    case _ => None
  } 

  def plusRankplusFile: Option[Position] = (rank.plusOne, file.plusOne) match {
    case (Some(r), Some(f)) => Some(new Position(file.plusOne.get, rank.plusOne.get))
    case _ => None
  }

  def plusRankminusFile: Option[Position] = (rank.plusOne, file.minusOne) match {
    case (Some(r), Some(f)) => Some(new Position(file.plusOne.get, rank.minusOne.get))
    case _ => None
  }

  def minusRankplusFile: Option[Position] = (rank.minusOne, file.plusOne) match {
    case (Some(r), Some(f)) => Some(new Position(file.minusOne.get, rank.plusOne.get))
    case _ => None
  }
  def minusRankminusFile: Option[Position] = (rank.minusOne, file.minusOne) match {
    case (Some(r), Some(f)) => Some(new Position(file.minusOne.get, rank.minusOne.get))
    case _ => None
  }

 
}

object Position {
  def apply(file: Char, rank: Int) = { 
    new Position(File(file),Rank(rank))
  }

  def plusFile(position: Position): Option[Position] = position.plusFile
  def minusFile(position: Position): Option[Position] = position.minusFile
  def plusRank(position: Position): Option[Position] = position.plusRank
  def minusRank(position: Position): Option[Position] = position.minusRank
}