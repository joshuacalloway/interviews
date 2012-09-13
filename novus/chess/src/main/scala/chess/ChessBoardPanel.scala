package chess

import scala.swing._
import scala.swing.event._

case class ChessBoardSquare(position: Position, squareColor: Color, private var piece: Piece = null) extends GridPanel(1,1) {
  background = getAwtSquareColor
  val button = new Button {
    background = getAwtSquareColor
    text = getButtonText
    foreground = getAwtPieceColor
    //listenTo(mouse.clicks)
    //reactions += {
    //  case e: MouseClicked => println("mouse clicked on square : " + position)
   // }
  }

  contents += button

  private def getButtonText = if (piece == null) "" else piece.name

  def getAwtPieceColor = if (piece == null) getAwtSquareColor else if (piece.color == white) java.awt.Color.white else java.awt.Color.black
             
  def getAwtSquareColor = if (squareColor == white) java.awt.Color.lightGray else java.awt.Color.darkGray

  def setPiece(newPiece: Piece) = {
    piece = newPiece
    button.text = getButtonText
    button.foreground = getAwtPieceColor
  }
}

class ChessBoardPanel(game: Game) extends GridPanel(10,10) {
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

  contents += new Label("8")
  val squareA8 = ChessBoardSquare(Position(A,8),white)
  contents += squareA8
  val squareB8 = ChessBoardSquare(Position(B,8),black)
  contents += squareB8
  val squareC8 = ChessBoardSquare(Position(C,8),white)
  contents += squareC8
  val squareD8 = ChessBoardSquare(Position(D,8),black)
  contents += squareD8
  val squareE8 = ChessBoardSquare(Position(E,8),white)
  contents += squareE8
  val squareF8 = ChessBoardSquare(Position(F,8),black)
  contents += squareF8
  val squareG8 = ChessBoardSquare(Position(G,8),white)
  contents += squareG8
  val squareH8 = ChessBoardSquare(Position(H,8),black)
  contents += squareH8    
  contents += new Label("8")

  contents += new Label("7")
  val squareA7 = ChessBoardSquare(Position(A,7),black)
  contents += squareA7
  val squareB7 = ChessBoardSquare(Position(B,7),white)
  contents += squareB7
  val squareC7 = ChessBoardSquare(Position(C,7),black)
  contents += squareC7
  val squareD7 = ChessBoardSquare(Position(D,7),white)
  contents += squareD7
  val squareE7 = ChessBoardSquare(Position(E,7),black)
  contents += squareE7
  val squareF7 = ChessBoardSquare(Position(F,7),white)
  contents += squareF7
  val squareG7 = ChessBoardSquare(Position(G,7),black)
  contents += squareG7
  val squareH7 = ChessBoardSquare(Position(H,7),white)
  contents += squareH7    
  contents += new Label("7")

  contents += new Label("6")
  val squareA6 = ChessBoardSquare(Position(A,6),white)
  contents += squareA6
  val squareB6 = ChessBoardSquare(Position(B,6),black)
  contents += squareB6
  val squareC6 = ChessBoardSquare(Position(C,6),white)
  contents += squareC6
  val squareD6 = ChessBoardSquare(Position(D,6),black)
  contents += squareD6
  val squareE6 = ChessBoardSquare(Position(E,6),white)
  contents += squareE6
  val squareF6 = ChessBoardSquare(Position(F,6),black)
  contents += squareF6
  val squareG6 = ChessBoardSquare(Position(G,6),white)
  contents += squareG6
  val squareH6 = ChessBoardSquare(Position(H,6),black)
  contents += squareH6    
  contents += new Label("6")

  contents += new Label("5")
  val squareA5 = ChessBoardSquare(Position(A,5),black)
  contents += squareA5
  val squareB5 = ChessBoardSquare(Position(B,5),white)
  contents += squareB5
  val squareC5 = ChessBoardSquare(Position(C,5),black)
  contents += squareC5
  val squareD5 = ChessBoardSquare(Position(D,5),white)
  contents += squareD5
  val squareE5 = ChessBoardSquare(Position(E,5),black)
  contents += squareE5
  val squareF5 = ChessBoardSquare(Position(F,5),white)
  contents += squareF5
  val squareG5 = ChessBoardSquare(Position(G,5),black)
  contents += squareG5
  val squareH5 = ChessBoardSquare(Position(H,5),white)
  contents += squareH5    
  contents += new Label("5")


  contents += new Label("4")
  val squareA4 = ChessBoardSquare(Position(A,4),white)
  contents += squareA4
  val squareB4 = ChessBoardSquare(Position(B,4),black)
  contents += squareB4
  val squareC4 = ChessBoardSquare(Position(C,4),white)
  contents += squareC4
  val squareD4 = ChessBoardSquare(Position(D,4),black)
  contents += squareD4
  val squareE4 = ChessBoardSquare(Position(E,4),white)
  contents += squareE4
  val squareF4 = ChessBoardSquare(Position(F,4),black)
  contents += squareF4
  val squareG4 = ChessBoardSquare(Position(G,4),white)
  contents += squareG4
  val squareH4 = ChessBoardSquare(Position(H,4),black)
  contents += squareH4    
  contents += new Label("4")

  contents += new Label("3")
  val squareA3 = ChessBoardSquare(Position(A,3),black)
  contents += squareA3
  val squareB3 = ChessBoardSquare(Position(B,3),white)
  contents += squareB3
  val squareC3 = ChessBoardSquare(Position(C,3),black)
  contents += squareC3
  val squareD3 = ChessBoardSquare(Position(D,3),white)
  contents += squareD3
  val squareE3 = ChessBoardSquare(Position(E,3),black)
  contents += squareE3
  val squareF3 = ChessBoardSquare(Position(F,3),white)
  contents += squareF3
  val squareG3 = ChessBoardSquare(Position(G,3),black)
  contents += squareG3
  val squareH3 = ChessBoardSquare(Position(H,3),white)
  contents += squareH3    
  contents += new Label("3")

  contents += new Label("2")
  val squareA2 = ChessBoardSquare(Position(A,2),white)
  contents += squareA2
  val squareB2 = ChessBoardSquare(Position(B,2),black)
  contents += squareB2
  val squareC2 = ChessBoardSquare(Position(C,2),white)
  contents += squareC2
  val squareD2 = ChessBoardSquare(Position(D,2),black)
  contents += squareD2
  val squareE2 = ChessBoardSquare(Position(E,2),white)
  contents += squareE2
  val squareF2 = ChessBoardSquare(Position(F,2),black)
  contents += squareF2
  val squareG2 = ChessBoardSquare(Position(G,2),white)
  contents += squareG2
  val squareH2 = ChessBoardSquare(Position(H,2),black)
  contents += squareH2    
  contents += new Label("2")

  contents += new Label("1")
  val squareA1 = ChessBoardSquare(Position(A,1),black)
  contents += squareA1
  val squareB1 = ChessBoardSquare(Position(B,1),white)
  contents += squareB1
  val squareC1 = ChessBoardSquare(Position(C,1),black)
  contents += squareC1
  val squareD1 = ChessBoardSquare(Position(D,1),white)
  contents += squareD1
  val squareE1 = ChessBoardSquare(Position(E,1),black)
  contents += squareE1
  val squareF1 = ChessBoardSquare(Position(F,1),white)
  contents += squareF1
  val squareG1 = ChessBoardSquare(Position(G,1),black)
  contents += squareG1
  val squareH1 = ChessBoardSquare(Position(H,1),white)
  contents += squareH1    
  contents += new Label("1")

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

  val squares: List[ChessBoardSquare] = List(
      squareA1, squareB1, squareC1, squareD1, squareE1, squareF1, squareG1, squareH1,
      squareA2, squareB2, squareC2, squareD2, squareE2, squareF2, squareG2, squareH2,
      squareA3, squareB3, squareC3, squareD3, squareE3, squareF3, squareG3, squareH3,
      squareA4, squareB4, squareC4, squareD4, squareE4, squareF4, squareG4, squareH4,
      squareA5, squareB5, squareC5, squareD5, squareE5, squareF5, squareG5, squareH5,
      squareA6, squareB6, squareC6, squareD6, squareE6, squareF6, squareG6, squareH6,
      squareA7, squareB7, squareC7, squareD7, squareE7, squareF7, squareG7, squareH7,
      squareA8, squareB8, squareC8, squareD8, squareE8, squareF8, squareG8, squareH8)
  game.board.pieces.foreach (piece => place(piece) )



  def place(piece: Piece) = {
    squares.find { X => X.position == piece.position } match {
      case Some(square) => square.setPiece(piece)
      case _ => println("Warning could place piece " + piece.name + " on position " + piece.position)
    }
  }
}