package biz.ostw.loly.randompoints

import java.awt.{Color, Dimension, Graphics}
import javax.swing.JPanel

class Board extends JPanel {

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)

    g.setColor(Color.BLUE)
    g.drawOval(0, 0, 100, 100)

    g.setColor(Color.RED)
    g.drawOval(50, 50, 100, 100)

    g.setColor(Color.GREEN)
    g.drawOval(100, 100, 100, 100)
  }
}
