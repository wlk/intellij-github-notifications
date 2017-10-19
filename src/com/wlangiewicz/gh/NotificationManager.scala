package com.wlangiewicz.gh

import java.time.LocalDateTime

import com.intellij.notification.{NotificationListener, NotificationType, Notifications, Notification => INotification}

class NotificationManager {
  private val NotificationGroup = "GitHubNotificationGroup"

  private def makeNotificationBody(n: Notification, gitHubApi: GitHubApi) = {
    val url = gitHubApi.getSubjectHtmlUrl(n.subject)
    val html = s"<a href='$url'>${n.subject.title}</a></html>"
    println(html)
    html
  }

  def displayNotifications(notifications: List[Notification], lastSyncDate: LocalDateTime, gitHubApi: GitHubApi) = {
    notifications
      .filter(_.updated_at.isAfter(lastSyncDate))
      .foreach { n =>
        Notifications.Bus.notify(new INotification(NotificationGroup, "<html>New Github Notification", makeNotificationBody(n, gitHubApi), NotificationType.INFORMATION, new NotificationListener.UrlOpeningListener(true)))
      }
  }

}
