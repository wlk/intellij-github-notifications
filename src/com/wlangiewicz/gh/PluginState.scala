package com.wlangiewicz.gh

import java.time.LocalDateTime

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project

class PluginState(project: Project) {
  private val Prefix = this.getClass.getPackage.toString
  private val properties = PropertiesComponent.getInstance(project)
  private val GitHubKey = s"${Prefix}_gitHubKey"
  private val LastSyncDateKey = s"${Prefix}_lastSyncDate"

  def getGithubKey: String = {
    properties.getValue(GitHubKey)
  }

  def setGithubKey(value: String): Unit = {
    properties.setValue(GitHubKey, value)
  }

  def getLastSyncDate: LocalDateTime = {
    val value = properties.getValue(LastSyncDateKey)

    if (value == null) {
      // If there's no data, fetch last 30 days
      LocalDateTime.now().minusDays(30)
    } else {
      LocalDateTime.parse(value)
    }
  }

  def setLastSyncDate(value: LocalDateTime): Unit = {
    properties.setValue(LastSyncDateKey, value.toString)
  }

  def resetLastSyncDate(): Unit = {
    properties.unsetValue(LastSyncDateKey)
  }

}

