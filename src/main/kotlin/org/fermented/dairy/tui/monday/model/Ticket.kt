package org.fermented.dairy.tui.monday.model

import java.time.Instant
import java.time.LocalDate

data class Ticket(
    val id: String,
    val name: String,
    val status: String,
    val assignees: List<User>,
    val dueDate: LocalDate?,
    val description: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
    val projectId: String,
    val boardId: String
)
