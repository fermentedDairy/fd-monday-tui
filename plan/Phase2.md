# Phase 2: API Integration (High Priority) [PENDING]

1. **API Client Testing** - Implement unit tests using mock server for GraphQL client against a mock server, plus manual test suite excluded from Maven test target by default
2. **GraphQL Client Implementation** - Implement Monday.com API v2 GraphQL client with proper headers (Authorization, Content-Type, API-Version) using GraphQL Kotlin library recommended for TUI applications due to its lightweight nature and native coroutine support
3. **Authentication Flow Development** - Build secure token handling mechanism that prompts users once and persists tokens for subsequent sessions
4. **User Context Retrieval** - Implement `me` query to fetch logged-in user ID required for assignee filtering
5. **Board Retrieval** - Fetch all boards/projects from Monday.com for project selection functionality
6. **Status Column Definition Retrieval** - Retrieve status column definitions from boards to determine available statuses dynamically 