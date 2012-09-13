package chess

import scala.swing._

object GameGui extends SimpleSwingApplication {

  def checkStatus = new Label("")
  def turnLabel = new TextField {
    text = ""
    columns = 5
    editable = false
  }
  def game = Game(Board.createInitial)
  val chessBoardPanel = new ChessBoardPanel(game)

  def top = new MainFrame {
    title = "Simple Chess Game"
    //turnLabel.text = game.turn.name
    turnLabel.text = "xxxx"
    val turn = new FlowPanel(new Label("Turn: "), turnLabel, checkStatus)

    contents = new FlowPanel(chessBoardPanel, turn)
  }
}