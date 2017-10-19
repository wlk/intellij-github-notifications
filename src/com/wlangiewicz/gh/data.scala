package com.wlangiewicz.gh

import java.time.LocalDateTime

case class Subject(title: String, url: String)
case class Notification(id: String, unread: Boolean, subject: Subject, reason: String, updated_at: LocalDateTime)
