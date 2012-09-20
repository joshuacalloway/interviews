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
