package org.fermented.dairy.tui.monday

import org.fermented.dairy.tui.monday.config.AppConfig
import org.fermented.dairy.tui.monday.service.UserServiceImpl

fun main() {
    // Initialize the application components
    val appConfig = AppConfig()
    val userService = UserServiceImpl(appConfig)
    
    println("Monday.com TUI Client initialized")
    println("Current config: $appConfig")
}

