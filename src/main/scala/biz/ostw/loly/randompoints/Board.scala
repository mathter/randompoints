package biz.ostw.loly.randompoints

import java.awt.event.{MouseWheelEvent, MouseWheelListener}
import java.awt.geom.Point2D
import java.awt.{Color, Dimension, Graphics, Point}
import javax.swing.{JComponent, JPanel, Scrollable}

class Board extends JComponent {

  var startPoints: Array[Int] = _

  var values: Array[Point2D.Double] = _

  {
    this.reset(3, 1000)
  }

  def setStartPoint(index: Int, point: Point2D.Double): Unit = {
    if (index < this.startPoints.length) {

    }
  }

  def reset(startPointCount: Int, valuesCount: Int): Unit = {
    this.startPoints = Array(startPointCount)
    this.values = Array.ofDim[Point2D.Double](valuesCount)
  }

  def getStartPointsCount(): Int = {
    this.startPoints.length
  }

  def getValuesCount(): Int = {
    this.values.length
  }

  {
    this.addMouseWheelListener(new MouseWheelListener {
      override def mouseWheelMoved(e: MouseWheelEvent): Unit = {

        var dimension = Board.this.getPreferredSize

        dimension = new Dimension(dimension.width + e.getWheelRotation, dimension.height + e.getWheelRotation)

        Board.this.setPreferredSize(dimension)
        Board.this.revalidate()
      }
    })
  }

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
