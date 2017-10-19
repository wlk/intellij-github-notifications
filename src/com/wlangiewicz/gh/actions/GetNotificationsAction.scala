package com.wlangiewicz.gh.actions

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.wlangiewicz.gh._

class GetNotificationsAction extends AnAction("Get_Notifications") {
  println("Plugin Loaded5")
  val notificationManager = new NotificationManager

  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    new GitHubNotificationFetch(project, notificationManager).fetchAndDisplay()
  }
}
