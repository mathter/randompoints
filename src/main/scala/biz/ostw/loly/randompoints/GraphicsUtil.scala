package biz.ostw.loly.randompoints

import java.awt.{Point, Shape}
import java.awt.geom.Point2D

import scala.util.Try

object GraphicsUtil {
  def getCenterPoint(shape: Shape): Try[Point] = {
    Try(
      if (shape == null) {
        throw new IllegalArgumentException("shape can't be null!")
      } else {
        Option(shape.getBounds).map(r => {
          new Point(r.getCenterX.asInstanceOf[Int], r.getCenterY.asInstanceOf[Int])
        }).get
      }
    )
  }

  def getCenterPoint2D(shape: Shape): Try[Point2D] = {
    Try(
      if (shape == null) {
        throw new IllegalArgumentException("shape can't be null!")
      } else {
        Option(shape.getBounds2D).map(r => {
          new Point2D.Double(r.getCenterX, r.getCenterY)
        }).get
      }
    )
  }

  def calcPoint2D(p0: Point2D, p1: Point2D, factor: Double): Try[Point2D] = {
    Try({
      val xdiff = (p1.getX - p0.getX) * factor
      val ydiff = (p1.getY - p0.getY) * factor

      new Point2D.Double(p0.getX + xdiff, p0.getY + ydiff)
    }
    )
  }
}
