package biz.ostw.loly.randompoints

import java.awt.BorderLayout
import java.awt.event.{WindowAdapter, WindowEvent}
import javax.swing._

object MainFrame extends JFrame("Hi") {

  val boardView: BoardView = new BoardView

  {
    MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    MainFrame.addWindowListener(new WindowAdapter {
      override def windowClosing(e: WindowEvent) = {
        Preferences.position(e.getWindow.getBounds)
      }
    })

    MainFrame.getContentPane.add(this.createContent())
    MainFrame.pack()
    MainFrame.setBounds(Preferences.position())
  }

  private def createContent(): JPanel = {
    val panel = new JPanel(new BorderLayout)

    panel.add(this.createMainToolbar(), BorderLayout.NORTH)
    panel.add(this.boardView, BorderLayout.CENTER)

    panel
  }

  private def createMainToolbar(): JToolBar = {
    val toolBar = new JToolBar(SwingConstants.HORIZONTAL)

    toolBar.add(new ResetAction(this.boardView.model))
    toolBar.addSeparator()
    toolBar.add(new RecalcAction(this.boardView.model))

    toolBar
  }
}
