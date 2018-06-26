package biz.ostw.loly.randompoints

import java.awt.{Component, Dimension, GraphicsEnvironment}
import javax.swing.{JScrollPane, JViewport, ScrollPaneConstants, SwingUtilities}


class BoardView(private val board: Board = new Board) extends JScrollPane {
  {
    val screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment.getDefaultScreenDevice.getDefaultConfiguration.getBounds

    board.setPreferredSize(screenBounds.getSize)
    this.setViewportView(this.board)
    GraphicsUtil.getCenterPoint(screenBounds).map(this.getViewport.setViewPosition(_))
  }

  def model(): Model = {
    this.board.model
  }
}
