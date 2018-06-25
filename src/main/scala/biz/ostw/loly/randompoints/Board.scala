package biz.ostw.loly.randompoints

import java.awt._
import java.awt.event.{MouseEvent, MouseMotionListener, MouseWheelEvent, MouseWheelListener}
import java.awt.geom.{Ellipse2D, Point2D}

import javax.swing.JComponent
import javax.swing.event.MouseInputAdapter

class Board extends JComponent {

  var scale: Double = 1

  val model: Model = new Model

  var highLightIndex: Option[Int] = Option.empty[Int]

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

    val mouseAdapter = new MouseInputAdapter() {

      var dragMode: Boolean = _

      override def mouseDragged(e: MouseEvent): Unit = {
        Board.this.getGraphics.asInstanceOf[Graphics2D].draw(new Ellipse2D.Double(e.getPoint.x, e.getPoint.y, 10, 10))
        Board.this.revalidate()

        this.dragMode = true
      }

      override def mouseReleased(e: MouseEvent): Unit = {
        Board.this.highLightIndex.map(
          Board.this.model.startPoints.update(_, new Point2D.Double(e.getPoint.x, e.getPoint.y)))

        this.dragMode = false
      }

      override def mouseMoved(e: MouseEvent): Unit = {
        Board.this.highLightIndex = Board.this.model.startPoints.
          find(e.getPoint.distance(_) <= Preferences.pointSize()).
          fold(Option.empty[Int])(p =>
            Option(Board.this.model.startPoints.indexOf(p))
          )

        Board.this.repaint()
      }
    };

    this.addMouseWheelListener(mouseAdapter)
    this.addMouseMotionListener(mouseAdapter)
    this.addMouseListener(mouseAdapter)
  }

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)


    val g2d: Graphics2D = g.asInstanceOf[Graphics2D]

    g.setColor(Color.CYAN)
    for (p <- this.model.startPoints) {
      if (p != null) {
        this.drawStartPoint(g2d, p)
      }
    }

    this.highLightIndex.map(this.model.startPoints(_)).map(this.highLight(g2d, _))
  }

  def highLight(g: Graphics2D, p: Point2D.Double): Unit = {
    val half = Preferences.pointSize
    val s = new Ellipse2D.Double(p.x - half, p.y - half, Preferences.pointSize * 2, Preferences.pointSize * 2)

    g.setColor(Color.RED)
    g.draw(s)
  }

  def drawStartPoint(g: Graphics2D, p: Point2D.Double): Unit = {
    val half = Preferences.pointSize / 2
    val s = new Ellipse2D.Double(p.x - half, p.y - half, Preferences.pointSize, Preferences.pointSize)

    g.fill(s)
  }

  def drawOrdinalPoint(g: Graphics2D, p: Point2D.Double): Unit = {
  }
}
