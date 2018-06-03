package biz.ostw.loly.randompoints

import java.awt.geom.Point2D.Double

import scala.util.Try

class Model {

  var startPoints: Array[Double] = _

  var values: Array[Double] = _

  {
    this.reset(3, 1000)

    this.setStartPoint(0, new Double(20, 20))
    this.setStartPoint(1, new Double(100, 20))
    this.setStartPoint(2, new Double(20, 200))
  }

  def setStartPoint(index: Int, point: Double): Try[Unit] = {
    Try(
      if (index < 0) {
        throw new IllegalArgumentException("index must be >= 0 !")
      } else {
        if (point == null) {
          throw new IllegalArgumentException("point can't be null!")
        } else {
          this.startPoints(index) = point
        }
      }
    )
  }

  private def check(): Try[Unit] = {
    null
  }

  def reset(startPointCount: Int, valuesCount: Int): Unit = {
    this.startPoints = Array.ofDim(startPointCount)
    this.values = Array.ofDim[Double](valuesCount)
  }

  def getStartPointsCount(): Int = {
    this.startPoints.length
  }

  def getValuesCount(): Int = {
    this.values.length
  }
}
