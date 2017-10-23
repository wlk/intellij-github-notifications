package com.wlangiewicz.gh.actions

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.application.ApplicationManager
import com.wlangiewicz.gh._

import scala.annotation.tailrec

class StartBackgroundSyncAction extends AnAction("Get_Notifications") {
  val notificationManager = new NotificationManager



  def actionPerformed(event: AnActionEvent) {
    // Has to start new thread to avoid blocking UI
    // TODO: Still blocks the UI
    new Thread {
      override def run(): Unit = {
        val project = event.getProject
        val syncThread = new GitHubNotificationFetch(project, notificationManager)

        ApplicationManager.getApplication.executeOnPooledThread(new Runnable() {
          override def run() {
            ApplicationManager.getApplication.runReadAction(new Runnable() {
              @tailrec
              override def run() {
                println("Running....")
                syncThread.fetchAndDisplay()
                Thread.sleep(10 * 1000)
                run()
              }
            })
          }
        })
      }
    }.start()
  }
}
