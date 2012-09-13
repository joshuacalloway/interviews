package chess

import scala.swing._
import scala.swing.event._


case class ChessBoardSquare(parent : ChessBoardPanel, position: Position, squareColor: Color, private var piece: Piece = null) extends GridPanel(1,1) {
  background = getAwtSquareColor
  val button = new Button {
    background = getAwtSquareColor
    text = getButtonText
    foreground = getAwtPieceColor
  }
  listenTo(button)
  reactions += {
    case ButtonClicked(b) => {
      if (piece != null && piece.color == parent.game.turn) {
        val possiblePositions = piece.possiblePositions(parent.game.board)
        parent.squares.foreach { sq => sq.unselect }
        select
        possiblePositions.foreach { X => parent.squares.filter { Y => Y.position == X }.foreach { sq => sq.select }} 
      } else if (selected) {
        val selPieceSquare = parent.squares.filter { Y => Y.selected == true && Y.piece != null }
        selPieceSquare.head.piece.position = position
        parent.place(selPieceSquare.head.piece)
        selPieceSquare.head.piece = null
        selPieceSquare.head.refresh
        parent.squares.foreach { sq => sq.unselect }
        GameGui.nextTurn
      }

    }
  }
  contents += button

  private def getButtonText = if (piece == null) "" else piece.name

  var selected = false
  def refresh = {
    button.background = getAwtSquareColor
    button.text = getButtonText
    button.foreground = getAwtPieceColor
  }

  def unselect = {
    border = Swing.EmptyBorder(0)
    selected = false;
  }

  def select = {
    border = Swing.LineBorder(java.awt.Color.yellow)
    selected = true
  }

  def getAwtPieceColor = if (piece == null) getAwtSquareColor else if (piece.color == white) java.awt.Color.white else java.awt.Color.black
             
  def getAwtSquareColor = if (squareColor == white) java.awt.Color.lightGray else java.awt.Color.darkGray

  def setPiece(newPiece: Piece) = {
    piece = newPiece
    button.text = getButtonText
    button.foreground = getAwtPieceColor
  }

  def getButton = button
}

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

  contents += new Label("8")
  val squareA8 = ChessBoardSquare(this, Position(A,8),white)
  contents += squareA8
  val squareB8 = ChessBoardSquare(this, Position(B,8),black)
  contents += squareB8
  val squareC8 = ChessBoardSquare(this, Position(C,8),white)
  contents += squareC8
  val squareD8 = ChessBoardSquare(this, Position(D,8),black)
  contents += squareD8
  val squareE8 = ChessBoardSquare(this, Position(E,8),white)
  contents += squareE8
  val squareF8 = ChessBoardSquare(this, Position(F,8),black)
  contents += squareF8
  val squareG8 = ChessBoardSquare(this, Position(G,8),white)
  contents += squareG8
  val squareH8 = ChessBoardSquare(this, Position(H,8),black)
  contents += squareH8    
  contents += new Label("8")

  contents += new Label("7")
  val squareA7 = ChessBoardSquare(this, Position(A,7),black)
  contents += squareA7
  val squareB7 = ChessBoardSquare(this, Position(B,7),white)
  contents += squareB7
  val squareC7 = ChessBoardSquare(this, Position(C,7),black)
  contents += squareC7
  val squareD7 = ChessBoardSquare(this, Position(D,7),white)
  contents += squareD7
  val squareE7 = ChessBoardSquare(this, Position(E,7),black)
  contents += squareE7
  val squareF7 = ChessBoardSquare(this, Position(F,7),white)
  contents += squareF7
  val squareG7 = ChessBoardSquare(this, Position(G,7),black)
  contents += squareG7
  val squareH7 = ChessBoardSquare(this, Position(H,7),white)
  contents += squareH7    
  contents += new Label("7")

  contents += new Label("6")
  val squareA6 = ChessBoardSquare(this, Position(A,6),white)
  contents += squareA6
  val squareB6 = ChessBoardSquare(this, Position(B,6),black)
  contents += squareB6
  val squareC6 = ChessBoardSquare(this, Position(C,6),white)
  contents += squareC6
  val squareD6 = ChessBoardSquare(this, Position(D,6),black)
  contents += squareD6
  val squareE6 = ChessBoardSquare(this, Position(E,6),white)
  contents += squareE6
  val squareF6 = ChessBoardSquare(this, Position(F,6),black)
  contents += squareF6
  val squareG6 = ChessBoardSquare(this, Position(G,6),white)
  contents += squareG6
  val squareH6 = ChessBoardSquare(this, Position(H,6),black)
  contents += squareH6    
  contents += new Label("6")

  contents += new Label("5")
  val squareA5 = ChessBoardSquare(this, Position(A,5),black)
  contents += squareA5
  val squareB5 = ChessBoardSquare(this, Position(B,5),white)
  contents += squareB5
  val squareC5 = ChessBoardSquare(this, Position(C,5),black)
  contents += squareC5
  val squareD5 = ChessBoardSquare(this, Position(D,5),white)
  contents += squareD5
  val squareE5 = ChessBoardSquare(this, Position(E,5),black)
  contents += squareE5
  val squareF5 = ChessBoardSquare(this, Position(F,5),white)
  contents += squareF5
  val squareG5 = ChessBoardSquare(this, Position(G,5),black)
  contents += squareG5
  val squareH5 = ChessBoardSquare(this, Position(H,5),white)
  contents += squareH5    
  contents += new Label("5")


  contents += new Label("4")
  val squareA4 = ChessBoardSquare(this, Position(A,4),white)
  contents += squareA4
  val squareB4 = ChessBoardSquare(this, Position(B,4),black)
  contents += squareB4
  val squareC4 = ChessBoardSquare(this, Position(C,4),white)
  contents += squareC4
  val squareD4 = ChessBoardSquare(this, Position(D,4),black)
  contents += squareD4
  val squareE4 = ChessBoardSquare(this, Position(E,4),white)
  contents += squareE4
  val squareF4 = ChessBoardSquare(this, Position(F,4),black)
  contents += squareF4
  val squareG4 = ChessBoardSquare(this, Position(G,4),white)
  contents += squareG4
  val squareH4 = ChessBoardSquare(this, Position(H,4),black)
  contents += squareH4    
  contents += new Label("4")

  contents += new Label("3")
  val squareA3 = ChessBoardSquare(this, Position(A,3),black)
  contents += squareA3
  val squareB3 = ChessBoardSquare(this, Position(B,3),white)
  contents += squareB3
  val squareC3 = ChessBoardSquare(this, Position(C,3),black)
  contents += squareC3
  val squareD3 = ChessBoardSquare(this, Position(D,3),white)
  contents += squareD3
  val squareE3 = ChessBoardSquare(this, Position(E,3),black)
  contents += squareE3
  val squareF3 = ChessBoardSquare(this, Position(F,3),white)
  contents += squareF3
  val squareG3 = ChessBoardSquare(this, Position(G,3),black)
  contents += squareG3
  val squareH3 = ChessBoardSquare(this, Position(H,3),white)
  contents += squareH3    
  contents += new Label("3")

  contents += new Label("2")
  val squareA2 = ChessBoardSquare(this, Position(A,2),white)
  contents += squareA2
  val squareB2 = ChessBoardSquare(this, Position(B,2),black)
  contents += squareB2
  val squareC2 = ChessBoardSquare(this, Position(C,2),white)
  contents += squareC2
  val squareD2 = ChessBoardSquare(this, Position(D,2),black)
  contents += squareD2
  val squareE2 = ChessBoardSquare(this, Position(E,2),white)
  contents += squareE2
  val squareF2 = ChessBoardSquare(this, Position(F,2),black)
  contents += squareF2
  val squareG2 = ChessBoardSquare(this, Position(G,2),white)
  contents += squareG2
  val squareH2 = ChessBoardSquare(this, Position(H,2),black)
  contents += squareH2    
  contents += new Label("2")

  contents += new Label("1")
  val squareA1 = ChessBoardSquare(this, Position(A,1),black)
  contents += squareA1
  val squareB1 = ChessBoardSquare(this, Position(B,1),white)
  contents += squareB1
  val squareC1 = ChessBoardSquare(this, Position(C,1),black)
  contents += squareC1
  val squareD1 = ChessBoardSquare(this, Position(D,1),white)
  contents += squareD1
  val squareE1 = ChessBoardSquare(this, Position(E,1),black)
  contents += squareE1
  val squareF1 = ChessBoardSquare(this, Position(F,1),white)
  contents += squareF1
  val squareG1 = ChessBoardSquare(this, Position(G,1),black)
  contents += squareG1
  val squareH1 = ChessBoardSquare(this, Position(H,1),white)
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