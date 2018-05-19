package biz.ostw.loly.randompoints

import java.awt.Dimension
import javax.swing.{JScrollPane, JViewport, ScrollPaneConstants}


class BoardView extends JScrollPane {
  {
    val board = new Board
    board.setSize(new Dimension(2000, 2000))

    this.getViewport.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
    this.getViewport.add(new Board)
    this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS)
    this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS)
  }
}
