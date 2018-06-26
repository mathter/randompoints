package biz.ostw.loly.randompoints

import java.awt.geom.Point2D

trait ModelListener {
  def fireReset(model: Model)

  def fireAddStartPoint(model: Model, point: Point2D)

  def fireUpdateStartPoint(model: Model, index: Int, point: Point2D)

  def fireRecalc(model: Model)
}
