# Monday.com TUI Client

A terminal-based user interface application for viewing and managing Monday.com tickets and tasks.

## Purpose

This is a Kotlin-based TUI (Terminal User Interface) client that allows users to connect to Monday.com's official API and view their current ticket state directly in the terminal. It provides an alternative to web-based interfaces for users who prefer working in terminal environments.

## Features

- Connects to Monday.com using the official REST API
- Displays user's tickets/tasks in a terminal interface
- View ticket information including status, assignees, due dates
- Navigate through multiple boards and lists within Monday.com

## Technologies

- Built with Kotlin targeting JVM
- Terminal User Interface framework (framework selection pending)
- Monday.com Official REST API client

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Build the project:
   ```bash
   mvn clean package
   ```

3. Run the application:
   ```bash
   mvn exec:java
   ```

## Configuration

Before running, configure your Monday.com API token in the settings.

## Project Structure

- `src/main/kotlin/` - Main source code directory
- `pom.xml` - Maven build configuration