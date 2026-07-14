package org.fermented.dairy.tui.monday.config

import java.time.Duration

data class AppConfig(
    val apiToken: String? = null,
    val refreshTokenInterval: Duration = Duration.ofMinutes(5),
    val currentBoardId: String? = null,
    val currentUser: String? = null
)