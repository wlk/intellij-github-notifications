package com.wlangiewicz.gh.actions

import java.time.LocalDateTime

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.wlangiewicz.gh._

class GetNotificationsAction extends AnAction("Get_Notifications")  {
  println("Plugin Loaded4!")
  val notificationManager = new NotificationManager

  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    val state = new PluginState(project)
    val gitHubApi = new GitHubApi(state.getGithubKey)

    println("Getting Github Notifications2")
    val notifications = gitHubApi.getNotifications
    println(notifications)

    val lastSyncDate = state.getLastSyncDate

    notificationManager.displayNotifications(notifications, lastSyncDate, gitHubApi)

    state.setLastSyncDate(LocalDateTime.now)
  }
}
