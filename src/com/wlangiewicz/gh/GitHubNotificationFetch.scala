package com.wlangiewicz.gh

import java.time.{Clock, LocalDateTime}

import com.intellij.openapi.project.Project

class GitHubNotificationFetch(project: Project, notificationManager: NotificationManager) {
  val state = new PluginState(project)
  val gitHubApi = new GitHubApi(state.getGithubKey)

  def fetchAndDisplay(): Unit = {
    println("Getting Github Notifications2")
    val notifications = gitHubApi.getNotifications
    println(notifications)

    val lastSyncDate = state.getLastSyncDate

    val filteredNotification = notifications.filter(_.updated_at.isAfter(lastSyncDate))

    notificationManager.displayNotifications(filteredNotification, gitHubApi)

    state.setLastSyncDate(LocalDateTime.now(Clock.systemUTC()))
  }
}
