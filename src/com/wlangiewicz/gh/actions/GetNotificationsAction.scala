package com.wlangiewicz.gh.actions

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.application.ApplicationManager
import com.wlangiewicz.gh._


class GetNotificationsAction extends AnAction("Get_Notifications") {
  println("Plugin Loaded7")
  val notificationManager = new NotificationManager

  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject

    ApplicationManager.getApplication.executeOnPooledThread(new Runnable() {
      override def run() {
        ApplicationManager.getApplication.runReadAction(new Runnable() {
          override def run() {
            new GitHubNotificationFetch(project, notificationManager).fetchAndDisplay()
            println("finished displaying notifications")
          }
        })
      }
    })
  }
}
