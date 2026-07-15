package org.fermented.dairy.tui.monday.config

import java.time.Duration

data class AppConfig(
    val apiToken: String? = null,
    val refreshTokenInterval: Duration = Duration.ofMinutes(DEFAULT_REFRESH_INTERVAL_MINUTES.toLong()),
    val currentBoardId: String? = null,
    val currentUser: String? = null
) {
    companion object {
        private const val DEFAULT_REFRESH_INTERVAL_MINUTES = 5
    }
}
