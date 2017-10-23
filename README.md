# github-notifications

This is a IntelliJ IDEA plugin that integrates GitHub notifications into IntelliJ's native UI.  
Thanks to it you can receive GitHub notifications right in your IDE

![Screenshot](https://raw.githubusercontent.com/wlk/intellij-github-notifications/master/docs/notifications.png)


This plugin is still in early beta, not published to the official plugin repository.

# Usage

1. Setup your GitHub access token
2. Select menu item "Get GitHub Notifications" or press `Ctrl+Shift+g`
3. When notifications pop up, you can click on the body to be redirected to the issue/pull request website.

Support for fetching notifications in the background is in progress.

# Development

Plugin developed using Scala, with spray-json as JSON library - it's added explicitly to `/lib` folder.
Plugin ideally shouldn't add any new dependencies - all functionality should be based on what's already included as part of IntelliJ's SDK/dependencies/classpath.  
For example this plugin uses Apache HTTP client instead of more idiomatic clients that Scala projects use.  


This approach limits the size of the plugin and doesn't increase memory footprint more than required.