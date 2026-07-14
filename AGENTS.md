# OpenCode Agent Guidelines

## Project Structure
- Main entrypoint: `org.fermented.dairy.tui.monday.MainKt`
- Build system: Maven with Kotlin 2.4.0 targeting JVM 26
- UI framework: Mosaic (Jetpack Compose for TUI)

## Key Commands
- Run application: `mvn exec:java`
- Build project: `mvn clean package`
- Execute tests: `mvn test`

## API Integration
- Monday.com GraphQL API v2 at `https://api.monday.com/v2`
- Authentication: Personal access token in Authorization header with API-Version 2024-10
- Default query pattern uses `items_page_by_column_values` for filtering by assignee/status
- User context retrieved via `me` query to identify logged-in user ID

## Sprint Management
- Identify sprint boards by querying all boards and examining board kinds/columns
- Sprint timeline columns contain start/end dates used to determine current sprints
- Default view shows tickets assigned to logged-in user for current sprint

## Implementation Guidelines
- When implementing a section from the plan, mark that section as "DONE" in Plan.md

## Security
- API token stored securely after initial input
- Implement log off functionality to remove stored token