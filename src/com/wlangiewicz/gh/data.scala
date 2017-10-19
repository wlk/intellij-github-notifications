package com.wlangiewicz.gh

import java.time.LocalDateTime

case class Subject(title: String, url: String, latest_comment_url: Option[String])
case class Notification(id: String, url: String, unread: Boolean, subject: Subject, reason: String, updated_at: LocalDateTime)

case class HtmlUrl(html_url: String)