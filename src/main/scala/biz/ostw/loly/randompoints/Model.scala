package biz.ostw.loly.randompoints

import java.awt.geom.Point2D
import java.awt.geom.Point2D.Double

import scala.collection.mutable.ArrayBuffer
import scala.util.{Random, Try}

class Model {

  var startPoints: ArrayBuffer[Point2D] = new ArrayBuffer[Point2D]

  var valuesCount: Int = 5000

  var values: Option[Array[Point2D]] = Option.empty

  val modelListeners: ArrayBuffer[ModelListener] = new ArrayBuffer[ModelListener]

  {
    this.reset()

    this.setStartPoint(0, new Double(20, 20))
    this.setStartPoint(1, new Double(100, 20))
    this.setStartPoint(2, new Double(20, 200))
  }

  def setSize(size: Int): Unit = {
    this.valuesCount = size
  }

  def addStartPoint(point: Point2D): Try[Unit] = {
    Try(
      if (point == null) {
        throw new IllegalArgumentException("point can't be null!")
      } else {
        this.startPoints += point

        this.modelListeners.foreach(l =>
          Try(
            l.fireAddStartPoint(this, point)
          )
        )
      }
    )
  }

  def setStartPoint(index: Int, point: Point2D): Try[Unit] = {
    Try(
      if (index < 0) {
        throw new IllegalArgumentException("index must be >= 0 !")
      } else {
        if (point == null) {
          throw new IllegalArgumentException("point can't be null!")
        } else {

          this.startPoints(index) = point

          this.modelListeners.foreach(l =>
            Try(
              l.fireUpdateStartPoint(this, index, point)
            )
          )
        }
      }
    )
  }

  def recalc(): Unit = {
    val random = new Random()

    var current: Point2D = this.startPoints(random.nextInt(this.startPoints.length))

    this.values = Option(Array.ofDim(this.valuesCount))

    this.values.map(a =>
      for (i <- 0 to a.length) {
        val to: Point2D = this.startPoints(random.nextInt(this.startPoints.length))
        GraphicsUtil.calcPoint2D(current, to, 0.61).map(p => {
          a.update(i, p)
          current = p
        })
      })

    this.modelListeners.foreach(l => Try(
      l.fireRecalc(this)
    ))
  }

  def reset(): Unit = {
    this.startPoints.clear()
    this.values = Option.empty

    this.modelListeners.foreach(l =>
      Try(l.fireReset(this))
    )
  }

  def getStartPointsCount(): Int = {
    this.startPoints.length
  }

  def getValuesCount(): Int = {
    this.valuesCount
  }
}
