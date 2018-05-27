package biz.ostw.loly.randompoints

import java.awt.Rectangle
import java.util.ResourceBundle
import java.util.prefs.{Preferences => JPreferences}

object Preferences {
  val pref: JPreferences = JPreferences.userNodeForPackage(Preferences.getClass);

  val resources = ResourceBundle.getBundle("biz.ostw.loly.randompoints.messages")

  def position(): Rectangle = {
    new Rectangle(
      this.pref.getInt("x", 0),
      this.pref.getInt("y", 0),
      this.pref.getInt("width", 900),
      this.pref.getInt("height", 800)
    )
  }

  def position(rectangle: Rectangle): Unit = {
    this.pref.putInt("x", rectangle.x)
    this.pref.putInt("y", rectangle.y)
    this.pref.putInt("width", rectangle.width)
    this.pref.putInt("height", rectangle.height)
  }
}
