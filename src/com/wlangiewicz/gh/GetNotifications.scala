package com.wlangiewicz.gh

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class GetNotifications extends AnAction("Get_Notifications") with JsonFormats {
  println("Plugin Loaded3!")

  def actionPerformed(event: AnActionEvent) {
    println("Getting Github Notifications2")
    val allNotifications = notifications("https://api.github.com/notifications?access_token=XXX&all=true")
    println(convertToObjects(allNotifications))
  }

  private def notifications(url: String): String = {
    val client: HttpClient  = HttpClientBuilder.create().build()
    val request = new HttpGet(url)
    val response: HttpResponse = client.execute(request)
    val stringResponse = EntityUtils.toString(response.getEntity)
    stringResponse
   }

  private def convertToObjects(notifications: String): List[Notification] = {
    import spray.json._
    import spray.json.DefaultJsonProtocol._

    notifications.parseJson.convertTo[List[Notification]]
  }

}
