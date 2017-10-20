package com.wlangiewicz.gh

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import spray.json.DefaultJsonProtocol._
import spray.json._

class GitHubApi(gitHubKey: String) extends JsonFormats {
  private val client: HttpClient = HttpClientBuilder.create().build()

  def getNotifications: List[Notification] = {
    val url = s"https://api.github.com/notifications?access_token=$gitHubKey"
    val stringResponse = makeRequest(url)
    stringResponse
      .parseJson
      .convertTo[List[Notification]]
  }

  private def makeRequest(url: String): String = {
    val request = new HttpGet(url)
    val response: HttpResponse = client.execute(request)
    EntityUtils.toString(response.getEntity)
  }

  def notficationClickableUrl(s: Subject): String = {
    val url = s.url + s"?access_token=$gitHubKey"
    val stringResponse = makeRequest(url)
    stringResponse
      .parseJson
      .convertTo[HtmlUrl]
      .html_url
  }
}
