package biz.ostw.loly.randompoints

import java.awt.{Component, Dimension, GraphicsEnvironment}
import javax.swing.{JScrollPane, JViewport, ScrollPaneConstants, SwingUtilities}


class BoardView extends JScrollPane {
  {
    val board = new Board
    board.setPreferredSize(new Dimension(500, 500))
    this.setViewportView(board)
  }
}
