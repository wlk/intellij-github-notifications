package com.wlangiewicz.gh

import java.time.LocalDateTime

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project


class PluginState(project: Project) {
  private val prefix = this.getClass.getPackage.toString
  private val properties = PropertiesComponent.getInstance(project)
  private val gitHubKey = s"${prefix}_gitHubKey"
  private val lastSyncDateKey = s"${prefix}_lastSyncDate"

  def getGithubKey: String = {
    properties.getValue(gitHubKey)
  }

  def setGithubKey(value: String): Unit = {
    properties.setValue(gitHubKey, value)
  }

  def getLastSyncDate: LocalDateTime = {
    val value = properties.getValue(lastSyncDateKey)

    if (value == null) {
      // If there's no data, fetch last 30 days
      LocalDateTime.now().minusDays(30)
    } else {
      LocalDateTime.parse(value)
    }
  }

  def setLastSyncDate(value: LocalDateTime): Unit = {
    properties.setValue(lastSyncDateKey, value.toString)
  }

  def resetLastSyncDate(): Unit = {
    properties.unsetValue(lastSyncDateKey)
  }

}

