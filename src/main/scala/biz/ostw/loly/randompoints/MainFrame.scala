package biz.ostw.loly.randompoints

import java.awt.BorderLayout
import java.awt.event.{WindowAdapter, WindowEvent}
import javax.swing._

object MainFrame extends JFrame("Hi") {

  private var startPointsCountSpinner: JSpinner = null

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
    panel.add(this.createBoard(), BorderLayout.CENTER)

    panel
  }

  private def createBoard(): JScrollPane = {
    new BoardView()
  }

  private def createMainToolbar(): JToolBar = {
    val toolBar = new JToolBar(SwingConstants.HORIZONTAL)

    toolBar.add(this.createSpinner)
    toolBar.addSeparator()
    toolBar.add(new ResetAction)

    toolBar
  }

  private def createSpinner(): JSpinner = {
    if (this.startPointsCountSpinner == null) {
      val model: SpinnerNumberModel = new SpinnerNumberModel(3, 2, 50, 1)
      MainFrame.startPointsCountSpinner = new JSpinner(model)
    }

    this.startPointsCountSpinner
  }
}
