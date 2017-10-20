package com.wlangiewicz.gh.actions

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.wlangiewicz.gh._

class GetNotificationsAction extends AnAction("Get_Notifications") {
  println("Plugin Loaded7")
  val notificationManager = new NotificationManager

  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    // TODO: This shouldn't block the UI thread but it does
    new GitHubNotificationFetch(project, notificationManager).fetchAndDisplay()
    println("finished displaying notifications")
  }
}
