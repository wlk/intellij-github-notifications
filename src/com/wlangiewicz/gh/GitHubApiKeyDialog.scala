package com.wlangiewicz.gh

import com.intellij.openapi.actionSystem.{AnAction, AnActionEvent}
import com.intellij.openapi.ui.Messages

class GitHubApiKeyDialog extends AnAction("Api_Key_Dialog") {
  def actionPerformed(event: AnActionEvent) {
    val project = event.getProject
    val state = new PluginState(project)

    val existingGithubKey = state.getGithubKey

    val message = if(existingGithubKey == null || existingGithubKey.isEmpty) {
       "You have not provided GitHub API Key, enter it now"
    } else {
      s"Enter your new GitHub API Key, this will override your current API Key: $existingGithubKey"
    }

    val enteredGitHubKey: String = Messages.showInputDialog(project, message, "Github API Key", Messages.getQuestionIcon)

    state.setGithubKey(enteredGitHubKey)

    Messages.showMessageDialog(project, s"GitHub API Key saved ${state.getGithubKey}", "Information", Messages.getInformationIcon)
  }
}
