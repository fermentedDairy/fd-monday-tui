# Monday.com TUI Client

A terminal-based user interface application for viewing and managing Monday.com tickets and tasks.

## Purpose

This is a Kotlin-based TUI (Terminal User Interface) client that allows users to connect to Monday.com's official API and view their current ticket state directly in the terminal. It provides an alternative to web-based interfaces for users who prefer working in terminal environments.

## Features

- Connects to Monday.com using the official GraphQL API v2
- Displays user's tickets/tasks organized by status (todo, in progress, done)
- View ticket information including name, assignees, due dates, description
- Navigate through multiple boards and lists within Monday.com
- Filter tickets by assignee and status columns  
- Show only tickets assigned to logged-in user for current sprint by default
- Securely store API token after first input with log off functionality

## Technologies

- Built with Kotlin targeting JVM 26
- Terminal User Interface framework: Mosaic (Jetpack Compose for TUI)
- Monday.com Official GraphQL API v2 client

## Installation

1. Clone the repository:
   ```bash
   git clone git@github.com:fermentedDairy/fd-monday-tui.git
   ```

2. Build the project:
   ```bash
   ./mvnw clean package
   ```

3. Run the application:
   ```bash
   ./mvnw exec:java
   ```

## Configuration

Before running, configure your Monday.com API token in the settings.

## Project Structure

- `src/main/kotlin/` - Main source code directory
- `pom.xml` - Maven build configuration
- `AGENTS.md` - Developer guidelines for understanding project structure and workflows
- `spec.md` - Complete specification of features, UI design, and technical requirements
- `MondayApi.md` - Documentation of Monday.com GraphQL API integration patterns