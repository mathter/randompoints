package biz.ostw.loly.randompoints

import java.awt._
import java.awt.event.{MouseWheelEvent, MouseWheelListener}
import java.awt.geom.{Ellipse2D, Point2D}
import javax.swing.JComponent

class Board extends JComponent {

  var scale: Double = 1

  val model: Model = new Model

  {
    this.addMouseWheelListener(new MouseWheelListener() {
      override def mouseWheelMoved(e: MouseWheelEvent): Unit = {

        var dimension = Board.this.getPreferredSize

        dimension = new Dimension(dimension.width + e.getWheelRotation, dimension.height + e.getWheelRotation)

        Board.this.scale += e.getWheelRotation * 0.1
        Board.this.setPreferredSize(dimension)
        Board.this.revalidate()
      }
    })
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)


    val g2d: Graphics2D = g.asInstanceOf[Graphics2D]

    g.setColor(Color.RED)
    for (p <- this.model.startPoints) {
      if (p != null) {
        this.drawStartPoint(g2d, p)
      }
    }
  }

  def drawStartPoint(g: Graphics2D, p: Point2D.Double): Unit = {
    val s = new Ellipse2D.Double(p.x, p.y, 6, 6)

    g.fill(s)
  }

  def drawOrdinalPoint(g: Graphics2D, p: Point2D.Double): Unit = {

  }
}
