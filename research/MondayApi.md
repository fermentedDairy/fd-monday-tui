# Monday.com GraphQL API Documentation

## Authentication

The Monday.com GraphQL API uses personal V2 API tokens for authentication. All requests must include the token in the Authorization header:

```
Authorization: YOUR_API_KEY_HERE
Content-Type: application/json
API-Version: 2024-10
```

Example curl request:
```curl
curl --location 'https://api.monday.com/v2/' \
--header 'Authorization:YOUR_API_KEY_HERE' \
--header 'API-Version: 2024-10' \
--header 'Content-Type: application/json' \
--data '{"query":"query { boards (ids: 1234567890) {name}}"}'
```

## API Endpoint

The single GraphQL endpoint for all operations:
`https://api.monday.com/v2`

## Getting Tickets and Filtering

### Basic Board Items Query
To fetch items from a specific board with column values:

```graphql
query {
  boards(ids: [BOARD_ID]) {
    items_page(limit: 50) {
      items {
        id
        name
        column_values {
          id
          text
          value
        }
      }
    }
  }
}
```

### Filter Items by Assignee Column Value
To retrieve only items assigned to a specific user:

```graphql
query {
  boards(ids: [BOARD_ID]) {
    items_page_by_column_values(
      limit: 50,
      board_id: "BOARD_ID",
      columns: [
        {
          column_id: "assignee_column_id"
          compare_value: ["USER_ID"]
          operator: any_of
        }
      ]
    ) {
      items {
        id
        name
        column_values {
          id
          text
          value
        }
      }
    }
  }
}
```

### Get Current Sprint Items
To get items for the current sprint, you would:

1. Identify the sprint board and find its ID 
2. Use a search query or direct items retrieval with appropriate filters:
```graphql
query {
  boards(ids: [SPRINT_BOARD_ID]) {
    items_page(limit: 100) {
      items {
        id
        name
        column_values {
          id
          text
          value
        }
      }
    }
  }
}
```

### Filtering by Status or Other Columns
```graphql
query {
  boards(ids: [BOARD_ID]) {
    items_page_by_column_values(
      limit: 50,
      board_id: "BOARD_ID",
      columns: [
        {
          column_id: "status_column_id"
          compare_value: ["Done"]
          operator: any_of
        }
      ]
    ) {
      items {
        id
        name
        column_values {
          id
          text
          value
        }
      }
    }
  }
}
```

## Getting All Available Status Columns

To retrieve all status columns and their available labels from a board:

```graphql
query {
  boards(ids: [BOARD_ID]) {
    columns {
      id
      title
      type
      settings_str
    }
  }
}
```

For the schema of status column definitions:
```graphql
query {
  get_column_type_schema(
    type: status
  )
}
```

## User Context

### Get Current User ID
To identify the authenticated user and implement assignee-based filtering:

```graphql
query {
  me {
    id
    name
    email
  }
}
```

This query returns:
- `id`: The unique identifier of the logged-in user (required for filtering)
- `name`: The user's display name 
- `email`: The user's email address

## Sprint Management

### Finding Sprint Boards
To identify boards that contain sprint information, you can query all boards and filter by board kind or specific column types:

```graphql
query {
  boards(limit: 100) {
    id
    name
    board_kind
    columns {
      id
      title
      type
    }
  }
}
```

Sprint-specific columns typically include:
- `timeline` - for sprint dates and duration
- `status` - with sprints having status values like "Active", "Completed"

### Querying Sprint Items
Once you have a sprint board ID, retrieve items (sprints) from that board:

```graphql
query {
  boards(ids: [SPRINT_BOARD_ID]) {
    items_page(limit: 25) {
      items {
        id
        name
        column_values {
          id
          text
          value
        }
      }
    }
  }
}
```

The `timeline` column values contain the sprint's start and end dates, which can be used to determine current sprints.

## CRUD Operations

### Create Item (Mutation)
```graphql
mutation {
  create_item(
    board_id: "BOARD_ID",
    item_name: "New Ticket"
  ) {
    id
  }
}
```

### Update Item
```graphql
mutation {
  change_column_value(
    board_id: "BOARD_ID",
    item_id: "ITEM_ID", 
    column_id: "COLUMN_ID",
    value: "{\"personsAndTeams\":[{\"id\":\"USER_ID\",\"kind\":\"person\"}]}"
  ) {
    id
  }
}
```

### Delete Item (Archive)
```graphql
mutation {
  archive_item(
    item_id: "ITEM_ID"
  ) {
    id
  }
}
```

## Key Considerations

- All queries and mutations are sent as POST requests with JSON body 
- The API version header should be used to target a specific version (recommended: 2024-10)
- Personal access tokens grant permissions based on the user's role
- The GraphQL schema can be discovered using `get_column_type_schema` (MCP tools)
- Filtering is done via `items_page_by_column_values` for column-specific filtering
- Status columns support filtering with numeric index values rather than text labels

## Column Types Reference

The Monday.com API supports multiple column types including:
- `status` - For status columns with customizable labels and colors
- `date` - For date values  
- `numbers` - For numerical data
- `people` - For person assignments
- `text` - For text content
- `dropdown` - For dropdown selections
- `timeline` - For timeline tracking
- And 30+ other column types