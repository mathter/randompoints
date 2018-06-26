package biz.ostw.loly.randompoints

import java.awt.event.ActionEvent

import javax.swing.AbstractAction

class RecalcAction(val model: Model) extends AbstractAction(Preferences.resources.getString("action.recalc.name")) {

  override def actionPerformed(e: ActionEvent): Unit = {
    this.model.recalc
  }
}
