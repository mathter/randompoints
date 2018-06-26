package biz.ostw.loly.randompoints

import java.awt._
import java.awt.event._
import java.awt.geom.{Ellipse2D, Point2D}

import javax.swing.JComponent
import javax.swing.event.MouseInputAdapter

class Board(val model: Model = new Model) extends JComponent with ModelListener {

  var scale: Double = 1

  var highLightIndex: Option[Int] = Option.empty[Int]

  {
    this.model.modelListeners += this

    val mouseAdapter = new MouseInputAdapter() {


      override def mouseClicked(e: MouseEvent): Unit = {
        if (e.getModifiersEx == InputEvent.CTRL_DOWN_MASK) {
          Board.this.model.addStartPoint(e.getPoint)
        }
      }

      override def mouseDragged(e: MouseEvent): Unit = {
        Board.this.highLightIndex.map(i => {
          Board.this.model.startPoints.update(i, new Point2D.Double(e.getPoint.x, e.getPoint.y))
          Board.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
          repaint()
        }
        )
      }

      override def mouseWheelMoved(e: MouseWheelEvent): Unit = {

        var dimension = Board.this.getPreferredSize

        dimension = new Dimension(dimension.width + e.getWheelRotation, dimension.height + e.getWheelRotation)

        Board.this.scale += e.getWheelRotation * 0.1
        Board.this.setPreferredSize(dimension)
        Board.this.revalidate()
      }

      override def mouseReleased(e: MouseEvent): Unit = {
        Board.this.highLightIndex.map(i => {
          Board.this.model.startPoints.update(i, new Point2D.Double(e.getPoint.x, e.getPoint.y))
          Board.this.highLightIndex = Option.empty[Int]
          Board.this.setCursor(Cursor.getDefaultCursor);
        }
        )
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

    g.setColor(Color.BLUE)
    for (p <- this.model.startPoints) {
      if (p != null) {
        this.drawStartPoint(g2d, p)
      }
    }

    this.highLightIndex.map(this.model.startPoints(_)).map(this.highLight(g2d, _))

    this.model.values.map(_.foreach(p => {
      this.drawOrdinalPoint(g2d, p)
    }))
  }

  private def highLight(g: Graphics2D, p: Point2D): Unit = {
    val half = Preferences.pointSize
    val s = new Ellipse2D.Double(p.getX - half, p.getY - half, Preferences.pointSize * 2, Preferences.pointSize * 2)

    g.setColor(Color.RED)
    g.draw(s)
  }

  private def drawStartPoint(g: Graphics2D, p: Point2D): Unit = {
    val half = Preferences.pointSize / 2
    val s = new Ellipse2D.Double(p.getX - half, p.getY - half, Preferences.pointSize, Preferences.pointSize)

    g.fill(s)
  }

  private def drawOrdinalPoint(g: Graphics2D, p: Point2D): Unit = {
    val half = Preferences.pointSize / 2
    val s = new Ellipse2D.Double(p.getX - half, p.getY - half, Preferences.pointSize, Preferences.pointSize)

    g.setColor(Color.BLACK)
    g.draw(s)
  }

  override def fireReset(model: Model): Unit = {
    this.repaint()
  }

  override def fireAddStartPoint(model: Model, point: Point2D): Unit = {
    this.repaint()
  }

  override def fireUpdateStartPoint(model: Model, index: Int, point: Point2D): Unit = {
    this.repaint()
  }

  override def fireRecalc(model: Model): Unit = {
    this.repaint()
  }
}
