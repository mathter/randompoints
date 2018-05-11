package biz.ostw.loly.randompoints

import java.awt.Graphics
import javax.swing.JComponent

class Board extends JComponent {
  override def paintComponent(g: Graphics) = {
    g.drawString("Hello world!!!", g.getClipBounds.width / 2, g.getClipBounds.height / 2)
  }
}
