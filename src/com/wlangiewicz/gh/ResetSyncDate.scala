package com.wlangiewicz.gh

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.ui.Messages

class ResetSyncDate extends AnAction("Reset_Sync_date") {
  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    val state = new PluginState(project)

    state.resetLastSyncDate()

    Messages.showMessageDialog(project, s"Successful reset of 'Last Sync Date'", "Information", Messages.getInformationIcon)
  }
}
