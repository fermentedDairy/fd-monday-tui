package org.fermented.dairy.tui.monday.api

import org.fermented.dairy.tui.monday.model.Board
import org.fermented.dairy.tui.monday.model.Ticket
import org.fermented.dairy.tui.monday.model.User

interface MondayApiClient {
    fun getBoards(): List<Board>
    fun getTickets(boardId: String): List<Ticket>
    fun getUserContext(): User
    fun createTicket(ticketData: TicketCreateInput): Ticket
    fun updateTicket(ticketId: String, ticketData: TicketUpdateInput): Ticket
    fun deleteTicket(ticketId: String): Boolean
    
    // Input data classes for API operations
    data class TicketCreateInput(
        val name: String,
        val boardId: String,
        val columnValues: Map<String, Any>
    )
    
    data class TicketUpdateInput(
        val ticketId: String,
        val columnValues: Map<String, Any>
    )
}

