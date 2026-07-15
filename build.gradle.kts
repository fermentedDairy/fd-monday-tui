plugins {
    kotlin("jvm") version "2.4.0"
    application
    `java-library`
}

group = "org.fermented.dairy.tui.monday"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    
    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    
    // JSON Handling
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.3")
    
    // Apollo GraphQL Client
    implementation("com.apollographql.apollo:apollo-runtime:4.0.0")
    
    // Test dependencies
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.fermented.dairy.tui.monday.MainKt")
}

// Create a fat JAR with all dependencies
tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "org.fermented.dairy.tui.monday.MainKt"
        )
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}