package com.wlangiewicz.gh

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils

class GitHubApi(gitHubKey: String) extends JsonFormats {

  def getNotifications: List[Notification] = {
    val url = s"https://api.github.com/notifications?access_token=$gitHubKey"
    val client: HttpClient = HttpClientBuilder.create().build()
    val request = new HttpGet(url)
    val response: HttpResponse = client.execute(request)
    val stringResponse = EntityUtils.toString(response.getEntity)
    convertToObjects(stringResponse)
  }

  def getSubjectHtmlUrl(s: Subject): String = {
    val client: HttpClient = HttpClientBuilder.create().build()
    val request = new HttpGet(s.url + s"?access_token=$gitHubKey")
    val response: HttpResponse = client.execute(request)
    val stringResponse = EntityUtils.toString(response.getEntity)
    import spray.json._
    stringResponse.parseJson.convertTo[HtmlUrl].html_url
  }

  private def convertToObjects(notifications: String): List[Notification] = {
    import spray.json.DefaultJsonProtocol._
    import spray.json._

    notifications.parseJson.convertTo[List[Notification]]
  }
}
