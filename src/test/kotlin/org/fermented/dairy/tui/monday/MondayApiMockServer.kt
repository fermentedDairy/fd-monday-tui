package org.fermented.dairy.tui.monday

import org.mockserver.integration.ClientAndServer
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.mockserver.model.JsonBody

/**
 * Mock server utility for testing Monday.com API integrations.
 * This class provides convenient methods to set up mock responses for various API endpoints
 * without relying on actual network calls or class mocks.
 */
class MondayApiMockServer {
    private val server: ClientAndServer
    
    init {
        // Start the mock server on a random available port
        server = ClientAndServer.startClientAndServer()
    }
    
    /**
     * Configure the mock server to return success response for user context query
     */
    fun mockUserContext(userId: String = "12345", userName: String = "Test User", userEmail: String = "test@example.com") {
        val responseBody = """
            {
                "data": {
                    "me": {
                        "id": "$userId",
                        "name": "$userName",
                        "email": "$userEmail"
                    }
                }
            }
        """.trimIndent()
        
        server.`when`(
            HttpRequest.request()
                .withMethod("POST")
                .withPath("/v2")
                .withHeader("Authorization", ".*")
                .withHeader("Content-Type", "application/json")
                .withHeader("API-Version", "2024-10")
        )
        .respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(responseBody)
                .withHeader("Content-Type", "application/json")
        )
    }
    
    /**
     * Configure the mock server to return boards data
     */
    fun mockBoards(boards: List<BoardResponse>) {
        val responseBody = """
            {
                "data": {
                    "boards": [
                        ${boards.joinToString(", ") { it.toJson() }}
                    ]
                }
            }
        """.trimIndent()
        
        server.`when`(
            HttpRequest.request()
                .withMethod("POST")
                .withPath("/v2")
                .withHeader("Authorization", ".*")
                .withHeader("Content-Type", "application/json")
                .withHeader("API-Version", "2024-10")
        )
        .respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(responseBody)
                .withHeader("Content-Type", "application/json")
        )
    }
    
    /**
     * Configure the mock server to return tickets data for a specific board
     */
    fun mockTickets(boardId: String, tickets: List<TicketResponse>) {
        val responseBody = """
            {
                "data": {
                    "boards": [
                        {
                            "id": "$boardId",
                            "items_page": {
                                "items": [
                                    ${tickets.joinToString(", ") { it.toJson() }}
                                ]
                            }
                        }
                    ]
                }
            }
        """.trimIndent()
        
        server.`when`(
            HttpRequest.request()
                .withMethod("POST")
                .withPath("/v2")
                .withHeader("Authorization", ".*")
                .withHeader("Content-Type", "application/json")
                .withHeader("API-Version", "2024-10")
        )
        .respond(
            HttpResponse.response()
                .withStatusCode(200)
                .withBody(responseBody)
                .withHeader("Content-Type", "application/json")
        )
    }
    
    /**
     * Configure the mock server for an error response
     */
    fun mockErrorResponse(statusCode: Int = 401, message: String = "Unauthorized") {
        val responseBody = """
            {
                "errors": [
                    {
                        "message": "$message"
                    }
                ]
            }
        """.trimIndent()
        
        server.`when`(
            HttpRequest.request()
                .withMethod("POST")
                .withPath("/v2")
                .withHeader("Authorization", ".*")
                .withHeader("Content-Type", "application/json")
                .withHeader("API-Version", "2024-10")
        )
        .respond(
            HttpResponse.response()
                .withStatusCode(statusCode)
                .withBody(responseBody)
                .withHeader("Content-Type", "application/json")
        )
    }
    
    /**
     * Get the mock server's port for configuring API clients
     */
    fun getPort(): Int {
        return server.localPort
    }
    
    /**
     * Stop the mock server
     */
    fun stop() {
        server.stop()
    }
    
    /**
     * Reset the mock server to its initial state
     */
    fun reset() {
        server.reset()
    }
    
    data class BoardResponse(
        val id: String,
        val name: String,
        val boardKind: String? = null,
        val columns: List<ColumnResponse>? = emptyList()
    ) {
        fun toJson(): String {
            return """
                {
                    "id": "$id",
                    "name": "$name"${if (boardKind != null) """, "board_kind": "$boardKind"""" else ""}
                }
            """.trimIndent()
        }
    }
    
    data class ColumnResponse(
        val id: String,
        val title: String,
        val type: String
    ) {
        fun toJson(): String {
            return """
                {
                    "id": "$id",
                    "title": "$title",
                    "type": "$type"
                }
            """.trimIndent()
        }
    }
    
    data class TicketResponse(
        val id: String,
        val name: String,
        val columnValues: Map<String, Any>? = emptyMap()
    ) {
        fun toJson(): String {
            return """
                {
                    "id": "$id",
                    "name": "$name"
                }
            """.trimIndent()
        }
    }
}