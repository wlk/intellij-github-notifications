package com.wlangiewicz.gh

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

class GetNotifications extends AnAction("Get_Notifications") {
  println("Plugin Loaded2!")

  def actionPerformed(event: AnActionEvent) {
    println("Getting Github Notifications2")
    notifications("https://api.github.com/notifications?access_token=XXXXX&all=true")
  }

  private def notifications(url: String) = {
    val client: HttpClient  = HttpClientBuilder.create().build()
    val request = new HttpGet(url)
    val response: HttpResponse = client.execute(request)
    println(response.getEntity.getContent.toString)
   }

}
