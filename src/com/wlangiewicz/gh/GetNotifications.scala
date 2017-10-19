package com.wlangiewicz.gh

import com.intellij.notification.{NotificationListener, NotificationType, Notifications, Notification => INotification}
import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
class GetNotifications extends AnAction("Get_Notifications") with JsonFormats {
  println("Plugin Loaded3!")
  private val NotificationGroup = "GitHubNotificationGroup"
  private val Token = "XXX"

  def actionPerformed(event: AnActionEvent) {
    println("Getting Github Notifications2")
    val allNotifications = getNotifications(s"https://api.github.com/notifications?access_token=$Token&all=false")
    val notifications = convertToObjects(allNotifications)
    println(notifications)

    def makeNotificationBody(n: Notification) = {
      val url = getSubjectHtmlUrl(n.subject)
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

  private def getSubjectHtmlUrl(s: Subject): String = {
    val client: HttpClient = HttpClientBuilder.create().build()
    val request = new HttpGet(s.url + s"?access_token=$Token")
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
