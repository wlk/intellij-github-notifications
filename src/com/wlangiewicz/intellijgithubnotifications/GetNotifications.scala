package com.wlangiewicz.intellijgithubnotifications

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent, PlatformDataKeys}
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class GetNotifications extends AnAction("Text_Boxes") {
  def actionPerformed(event: AnActionEvent) {
    println("Getting Github Notifications")
  }
}
