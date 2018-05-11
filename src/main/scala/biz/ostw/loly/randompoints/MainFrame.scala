package biz.ostw.loly.randompoints

import java.awt.Point
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
    val board = new Board
    board.setSize(2000, 2000)

    val scrollPane = new JScrollPane()
    scrollPane.getViewport.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
    scrollPane.getViewport.add(board)
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)

    scrollPane.getViewport.setViewPosition(new Point(1000,1000))

    scrollPane
  }
}
