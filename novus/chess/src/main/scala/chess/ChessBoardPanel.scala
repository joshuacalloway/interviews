package chess

import scala.swing._
import scala.swing.event._

case class ChessBoardPanel(game: Game) extends GridPanel(10,10) {
  contents += new Label("")
  contents += new Label("a")
  contents += new Label("b")
  contents += new Label("c")
  contents += new Label("d")
  contents += new Label("e") 
  contents += new Label("f")
  contents += new Label("g")
  contents += new Label("h")
  contents += new Label("")

  var squares: List[ChessBoardSquare] = List[ChessBoardSquare]()
  val allFiles = List[File](A, B, C, D, E, F, G, H)
  val allRanks = List[Rank](eight, seven, six, five, four, three, two, one)

  
  for (r <- allRanks) {
    var color:Color = if (r.value % 2 == 0) black else white
    contents += new Label(r.value.toString)
    for (f <- allFiles) {
      color = Color.other(color)
      val square = ChessBoardSquare(this, Position(f,r.value), color)
      contents += square
      squares = square :: squares
    }
    contents += new Label(r.value.toString)
  }
  
  contents += new Label("")
  contents += new Label("a")
  contents += new Label("b")
  contents += new Label("c")
  contents += new Label("d")
  contents += new Label("e") 
  contents += new Label("f")
  contents += new Label("g")
  contents += new Label("h")
  contents += new Label("")

/*
  val squares: List[ChessBoardSquare] = List(
      squareA1, squareB1, squareC1, squareD1, squareE1, squareF1, squareG1, squareH1,
      squareA2, squareB2, squareC2, squareD2, squareE2, squareF2, squareG2, squareH2,
      squareA3, squareB3, squareC3, squareD3, squareE3, squareF3, squareG3, squareH3,
      squareA4, squareB4, squareC4, squareD4, squareE4, squareF4, squareG4, squareH4,
      squareA5, squareB5, squareC5, squareD5, squareE5, squareF5, squareG5, squareH5,
      squareA6, squareB6, squareC6, squareD6, squareE6, squareF6, squareG6, squareH6,
      squareA7, squareB7, squareC7, squareD7, squareE7, squareF7, squareG7, squareH7,
      squareA8, squareB8, squareC8, squareD8, squareE8, squareF8, squareG8, squareH8)
*/
  game.board.pieces.foreach (piece => place(piece) )


  def place(piece: Piece) = {
    squares.find { X => X.position == piece.position } match {
      case Some(square) => square.setPiece(piece)
      case _ => println("Warning could not place piece " + piece.name + " on position " + piece.position)
    }
  }
}