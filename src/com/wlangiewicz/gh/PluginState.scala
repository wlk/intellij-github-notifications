package com.wlangiewicz.gh

import java.time.LocalDateTime

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.project.Project


class PluginState(project: Project) {
  private val prefix = "com.wlangiewicz.gh"
  private val properties = PropertiesComponent.getInstance(project)
  private val gitHubKey = s"${prefix}_gitHubKey"
  private val lastSyncDateKey = s"${prefix}_lastSyncDate"

  def getGithubKey: String = {
    properties.getValue(gitHubKey)
  }

  def getLastSyncDate: LocalDateTime = {
    LocalDateTime.parse(properties.getValue(lastSyncDateKey))
  }

  def setGithubKey(value: String) = {
    properties.setValue(gitHubKey, value)
  }

  def setLastSyncDate(value: LocalDateTime) = {
    properties.setValue(lastSyncDateKey, value.toString)
  }

}

