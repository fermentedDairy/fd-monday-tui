# GraphQL Client Libraries for Kotlin - TUI Application Research

After researching popular GraphQL client libraries for Kotlin that would be appropriate for a TUI application like the monday.com dashboard, I've identified two main contenders that meet all specified requirements:

## 1. GraphQL Kotlin (Expedia Group)

### Features Summary:
- **HTTP Clients**: Provides Ktor HTTP client and Spring WebClient based reference implementations
- **Type-safe queries/mutations**: Automatic generation of type-safe Kotlin models at build time using Gradle/Maven plugins
- **Async/suspend functions**: Native support for coroutines with suspendable execute methods  
- **Documentation & Community**: Good documentation, active development, community on GitHub and Slack
- **Lightweight & Performant**: Lightweight libraries focused on type-safety and performance

### Key Advantages:
- Built specifically for Kotlin/JVM with native coroutine support
- Supports both Ktor (recommended for TUI apps) and Spring WebClient options  
- Generates clean, type-safe Kotlin models using either `kotlinx.serialization` or Jackson
- Fully asynchronous non-blocking communication through Kotlin coroutines
- Can customize HTTP client configuration including engines, serializers, and features

### Code Examples:
```kotlin
import com.expediagroup.graphql.client.ktor.GraphQLKtorClient
// Create client with suspend functions
val client = GraphQLKtorClient(url = "http://localhost:8080/graphql")
// Execute query - automatically suspends until response
val result = client.execute(helloWorldQuery)
```

## 2. Apollo Kotlin

### Features Summary:
- **HTTP Clients**: Uses OkHttp for Android/JVM, supports custom HTTP engine implementations  
- **Type-safe queries/mutations**: Strongly-typed GraphQL operations with automatic code generation
- **Async/suspend functions**: Suspend functions for non-blocking operations (Kotlin JVM)
- **Documentation & Community**: Excellent documentation, active community, IntelliJ plugin support
- **Lightweight & Performant**: Feature-rich but can be configured minimally for lightweight use

### Key Advantages:
- Industry-leading GraphQL client with excellent caching capabilities
- Full support for queries, mutations, subscriptions
- Multi-platform compatibility (including JVM)
- Comprehensive documentation and developer tools
- Supports various HTTP engines including Ktor integration via custom `HttpEngine` implementation

### Code Examples: 
```kotlin
import com.apollographql.apollo.ApolloClient
// Create client using OkHttp by default but can be customized  
val apolloClient = ApolloClient.Builder()
    .serverUrl("https://example.com/graphql")
    .build()

// Execute query - suspend function
val response = apolloClient.query(HeroQuery(id = "1")).execute()
```

## Recommendation for TUI Application

For a TUI application, **GraphQL Kotlin** would be the preferred choice due to:

1. **Native coroutine support**: Built specifically with Kotlin's coroutine model in mind
2. **Ktor integration**: Perfect for lightweight TUI applications leveraging Ktor's features  
3. **Simpler setup**: Fewer dependencies and easier configuration for a standalone CLI/TUI tool
4. **Generated client models**: Clean type-safe operations that map directly to GraphQL schema
5. **Asynchronous non-blocking I/O**: Essential for responsive terminal interfaces

The Apollo Kotlin library, while more feature-rich with its caching capabilities and comprehensive tooling, would be overkill for this specific use case where the primary need is simple query execution without persistent data storage or complex caching requirements.

Both libraries support suspend functions and are well-documented with strong community backing.