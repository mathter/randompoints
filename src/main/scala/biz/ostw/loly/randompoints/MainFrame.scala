package biz.ostw.loly.randompoints

import java.awt.{Dimension, Point, Rectangle}
import java.awt.event.{WindowAdapter, WindowEvent}
import java.util.prefs.Preferences
import javax.swing._

object MainFrame extends JFrame("Hi") {
  {
    MainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)

    MainFrame.addWindowListener(new WindowAdapter {
      override def windowClosing(e: WindowEvent) = {
        Preferences.position(e.getWindow.getBounds)
      }
    })

    MainFrame.getContentPane.add(this.createBoard())
    MainFrame.pack()
    MainFrame.setBounds(Preferences.position())
  }

  private def createBoard(): JScrollPane = {
    new BoardView
  }
}
