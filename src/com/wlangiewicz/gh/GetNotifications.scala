package com.wlangiewicz.gh

import com.intellij.notification.{NotificationListener, NotificationType, Notifications, Notification => INotification}
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class GetNotifications extends AnAction("Get_Notifications") with JsonFormats {
  println("Plugin Loaded4!")
  private val NotificationGroup = "GitHubNotificationGroup"

  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    val state = new PluginState(project)
    val gitHubKey = state.getGithubKey

    println("Getting Github Notifications2")
    val allNotifications = getNotifications(s"https://api.github.com/notifications?access_token=$gitHubKey&all=false")
    val notifications = convertToObjects(allNotifications)
    println(notifications)

    def makeNotificationBody(n: Notification) = {
      val url = getSubjectHtmlUrl(n.subject, gitHubKey)
      val html = s"<a href='$url'>${n.subject.title}</a></html>"
      println(html)
      html
    }

    notifications.foreach { n =>
      Notifications.Bus.notify(new INotification(NotificationGroup, "<html>New Github Notification", makeNotificationBody(n), NotificationType.INFORMATION, new NotificationListener.UrlOpeningListener(true)))
    }

  }

  private def getNotifications(url: String): String = {
    val client: HttpClient = HttpClientBuilder.create().build()
    val request = new HttpGet(url)
    val response: HttpResponse = client.execute(request)
    val stringResponse = EntityUtils.toString(response.getEntity)
    stringResponse
  }

  private def getSubjectHtmlUrl(s: Subject, gitHubKey: String): String = {
    val client: HttpClient = HttpClientBuilder.create().build()
    val request = new HttpGet(s.url + s"?access_token=$gitHubKey")
    val response: HttpResponse = client.execute(request)
    val stringResponse = EntityUtils.toString(response.getEntity)
    import spray.json._
    stringResponse.parseJson.convertTo[HtmlUrl].html_url
  }

  private def convertToObjects(notifications: String): List[Notification] = {
    import spray.json._
    import spray.json.DefaultJsonProtocol._

    notifications.parseJson.convertTo[List[Notification]]
  }

}
