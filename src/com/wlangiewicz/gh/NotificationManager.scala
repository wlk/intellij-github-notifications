package com.wlangiewicz.gh

import com.intellij.notification.{NotificationListener, NotificationType, Notifications, Notification => IdeaNotification}

class NotificationManager {
  private val NotificationGroup = "GitHubNotificationGroup"

  private def makeNotificationBody(notification: Notification, gitHubApi: GitHubApi) = {
    val url = gitHubApi.notficationClickableUrl(notification.subject)
    val html = s"<a href='$url'>${notification.subject.title}</a></html>"
    println(html)
    html
  }

  def displayNotifications(notifications: List[Notification], gitHubApi: GitHubApi): Unit = {
    notifications
      .foreach { notification =>
        Notifications.Bus.notify(makeNotification(notification, gitHubApi))
      }
  }

  private def displayType(subject: Subject): String = {
    subject.`type` match {
      case "PullRequest" => "New Pull Request"
      case s => s
    }
  }

  private def makeNotification(notification: Notification, gitHubApi: GitHubApi) = {
    new IdeaNotification(
      NotificationGroup,
      s"<html>GitHub Notification for ${displayType(notification.subject)}",
      makeNotificationBody(notification, gitHubApi),
      NotificationType.INFORMATION,
      new NotificationListener.UrlOpeningListener(true)
    )
  }

}
