# Monday.com TUI Client - Specification

## Overview
A terminal-based user interface application for managing Monday.com tickets with full CRUD operations and status-based views.

## Functional Requirements

### Core Features
- Connect to Monday.com official REST API using personal access token
- Display tickets organized by status (todo, in progress, done)
- View ticket details including name, assignees, due date, description
- Create new tickets
- Edit existing tickets (name, status, assignee, due date)
- Delete tickets
- Mark tickets as complete
- Securely store API token after first input
- Log off functionality to securely delete stored token
- Show tickets for current sprint by default
- Show only tickets assigned to logged in user by default

### UI Requirements
- Status-based view organization
- Keyboard navigation using arrow keys and enter
- Quick action shortcuts for common operations
- Terminal window with responsive layout
- Clear visual distinction between different ticket statuses
- Search/filter capabilities within each status group

## Technical Requirements

### API Integration
- Official Monday.com REST API v2 implementation
- Secure handling of authentication token (personal access token)
- Error handling for network issues and API limitations
- Support for pagination when retrieving large datasets

### Data Management
- Local caching of ticket data for better performance
- Synchronization with Monday.com on demand or automatically
- Data persistence between application sessions

### UI Framework
- TUI framework implementation (framework selection pending)
- Responsive layout that adapts to terminal size
- Smooth scrolling through tickets
- Clear visual feedback for user actions

## UI Design Mockups

### Main Dashboard Layout
```
┌────────────────────────────────────────────────────────────────┐
│ Monday.com TUI Client - Logged in as: John Smith               │
├────────────────────────────────────────────────────────────────┤
│ Boards: [Project Alpha] [Project Beta] [All Projects]          │
├────────────────────────────────────────────────────────────────┤
│ Status: TODO          ┌──────────────────────────────────────┐ │
│                       │ 1. Fix login bug                     │ │
│                       │   Assignee: John Smith               │ │
│                       │   Due: Today                         │ │
│                       │                                      │ │
│                       │ 2. Update documentation              │ │
│                       │   Assignee: Jane Doe                 │ │
│                       │   Due: Tomorrow                      │ │
│                       │                                      │ │
│                       └──────────────────────────────────────┘ │
├────────────────────────────────────────────────────────────────┤
│ Status: IN PROGRESS   ┌──────────────────────────────────────┐ │
│                       │ 3. Implement feature X               │ │
│                       │   Assignee: Bob Johnson              │ │
│                       │   Due: 5 days                        │ │
│                       │                                      │ │
│                       └──────────────────────────────────────┘ │
├────────────────────────────────────────────────────────────────┤
│ Status: DONE          ┌──────────────────────────────────────┐ │
│                       │ 4. Complete user testing             │ │
│                       │   Assignee: Alice Brown              │ │
│                       │   Due: 2 weeks ago                   │ │
│                       │                                      │ │
│                       └──────────────────────────────────────┘ │
├────────────────────────────────────────────────────────────────┤
│ [Create] [Edit] [Delete] [Refresh] [Help]                      │
└────────────────────────────────────────────────────────────────┘
```

### Status Drill-down View
```
┌────────────────────────────────────────────────────────────────┐
│ Monday.com TUI Client - Logged in as: John Smith               │
├────────────────────────────────────────────────────────────────┤
│ Boards: [Project Alpha] [Project Beta] [All Projects]          │
├────────────────────────────────────────────────────────────────┤
│ Status: TODO          ┌─────────────────────────────────────┐  │
│                       │ 1. Fix login bug                    │  │
│                       │   Assignee: John Smith              │  │
│                       │   Due: Today                        │  │
│                       │                                     │  │
│                       │ 2. Update documentation             │  │
│                       │   Assignee: Jane Doe                │  │
│                       │   Due: Tomorrow                     │  │
│                       │                                     │  │
│                       └─────────────────────────────────────┘  │
├────────────────────────────────────────────────────────────────┤
│ [Back] [Filter by Assignee] [Filter by Date] [Create Ticket]   │
└────────────────────────────────────────────────────────────────┘
```

### Keyboard Shortcuts Reference
```
Navigation:
  ↑/↓      - Move between tickets
  ←/→      - Switch between status columns
  Enter    - Open ticket details
  Esc      - Return to main view

Actions:
  Ctrl+Alt+C - Create new ticket
  Ctrl+Alt+E - Edit selected ticket
  Ctrl+Alt+D - Delete selected ticket
  Ctrl+Alt+R - Refresh data
  Ctrl+Alt+H - Show help
  Ctrl+Alt+L - Log off
```

## User Interaction Flow

1. Application starts and prompts for API token
2. Fetches all boards/projects from Monday.com
3. Displays main dashboard with status-based ticket views
4. User navigates between different statuses using arrow keys
5. Selecting a ticket displays detailed information
6. Users can:
   - Create new tickets with basic details
   - Edit existing ticket properties (name, assignee, status)
   - Delete tickets with confirmation
   - Mark tickets as complete

## Data Model

### Ticket Structure
- id: String
- name: String
- status: Enum (todo, in progress, done)
- description: String
- due_date: Date/DateTime
- assignees: List<String>
- project_id: String
- board_id: String

### API Endpoints Used
- GET /boards - Retrieve all boards and projects
- GET /items - Retrieve items from a specific board/list
- POST /items - Create new item/ticket
- PUT /items/{id} - Update existing item
- DELETE /items/{id} - Delete item

## Technical Implementation

### Framework Selection
The application will use **Mosaic** - a Kotlin library for building terminal UIs using Jetpack Compose. This choice provides:

- Declarative UI development matching modern Kotlin patterns
- Active maintenance and regular updates by JakeWharton (Compose lead)
- Familiar API for developers experienced with Android UI development 
- Good documentation and sample applications provided in the repository

### Build System
- Maven-based build system targeting JVM 11+
- Kotlin 1.9+ with Compose compiler plugin
- Standard Maven directory structure

### Data Model
```
Ticket {
    id: String
    name: String
    status: StatusEnum (TODO, IN_PROGRESS, DONE)
    assignees: List<User>
    dueDate: LocalDate?
    description: String?
    createdAt: Instant
    updatedAt: Instant
}

User {
    id: String
    name: String
    email: String
}
```

### API Integration
The application will connect to Monday.com GraphQL API using the official endpoints. This approach provides:

- More efficient data fetching compared to traditional REST APIs  
- Ability to request exactly the fields needed for each use case
- Better performance for complex queries involving multiple related objects
- Full support for all CRUD operations through GraphQL mutations

The application will:
- Use personal access token authentication 
- Fetch tickets for current sprint assigned to logged-in user by default
- Support fully CRUD operations through GraphQL endpoints
- Implement filtering by assignee and status columns using `items_page_by_column_values`
- Retrieve the logged-in user's ID using the `me` query to identify the authenticated user