
package chess

// http://en.wikipedia.org/wiki/Chessboard

case class Position(file: File, rank: Rank)

object Position {
    def apply(file: Char, rank: Int) = {
	new Position(a,one)
    }
}