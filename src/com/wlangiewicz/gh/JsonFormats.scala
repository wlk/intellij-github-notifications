package com.wlangiewicz.gh

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import spray.json._
import spray.json.DefaultJsonProtocol._

trait JsonFormats {
  implicit val LocalDateTimeFormat = new JsonFormat[LocalDateTime] {
    override def write(x: LocalDateTime) = JsString(x.toString)

    override def read(value: JsValue): LocalDateTime = value match {
      case JsString(x) => LocalDateTime.parse(x, DateTimeFormatter.ISO_DATE_TIME)
      case x           => deserializationError("Expected LocalDateTime as String, but got " + x)
    }
  }

  implicit val SubjectFormat = jsonFormat4(Subject)
  implicit val NotificationFormat = jsonFormat6(Notification)
  implicit val HtmlUrlFormat = jsonFormat1(HtmlUrl)
}