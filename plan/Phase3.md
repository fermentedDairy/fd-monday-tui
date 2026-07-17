# Phase 3: Core Functionality (High Priority) [PENDING]  

1. **Ticket CRUD Operations** - Implement all CRUD operations through GraphQL:
    - Create items with create_item mutation
    - Update items with change_column_value mutations  
    - Delete/Archive items with archive_item mutation
    - Retrieve items using boards and items_page_by_column_values queries
2. **Sprint Detection** - Identify sprint boards by querying all boards and examining board kinds/columns to determine current sprints
3. **Status Column Handling** - Process status column values dynamically based on board configuration rather than fixed enums 
4. **Core Functionality Testing** - Implement unit tests for core functionality classes using mocked GraphQL client, plus manual test suite excluded from Maven test target by default