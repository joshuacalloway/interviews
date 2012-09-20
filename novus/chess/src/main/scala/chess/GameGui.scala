package chess

import scala.swing._

object GameGui extends SimpleSwingApplication {

  val checkStatus = new Label("Game is in progress")

  val turnLabel = new TextField {
    text = "white"
    columns = 5
    editable = false
  }

  val game = Game(Board.createInitial)
  val chessBoardPanel = new ChessBoardPanel(game)

  def top = new MainFrame {
    title = "Simple Chess Game"
    val turn = new FlowPanel(new FlowPanel(new Label("Turn: "), turnLabel),checkStatus)
    contents = new FlowPanel(chessBoardPanel, turn)
  }

  def nextTurn = {
    game.nextTurn
    turnLabel.text = game.turn.name
    if (game.isCheckMate) checkStatus.text = "Check Mate, " + Color.other( game.turn ) + " won the game." else if (game.isCheck) checkStatus.text = "Check of " + game.turn.name + " King." else checkStatus.text = "Game is in progress"
  }

  def turn = game.turn
}