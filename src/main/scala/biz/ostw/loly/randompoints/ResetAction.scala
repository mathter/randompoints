package biz.ostw.loly.randompoints

import java.awt.event.ActionEvent
import javax.swing.AbstractAction

class ResetAction(val model: Model) extends AbstractAction(Preferences.resources.getString("action.reset.name")) {

  override def actionPerformed(e: ActionEvent): Unit = {
    model.reset()
  }
}
