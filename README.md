# Lab Exam - Question A

## Description

A team of developers is working on a Java application that needs to maintain a single connection to a SQLite database 
throughout the application's lifecycle. 
Currently, the code creates multiple database connections, which is inefficient and could lead to resource leaks. 
The team needs to implement a proper database connection manager using the Singleton design pattern to ensure only one 
database connection instance exists and is reused across the application.

## Requirements
- Maven
- JUnit 5
- SQLite JDBC Driver
- An IDE such as IntelliJ IDEA or editor such as VS Code
- SQLite database file will be created automatically in the project root directory

## Question
Complete the implementation of the `DatabaseConnection` class using the Singleton design pattern. The class should:
1. Ensure only one instance of the database connection exists
2. Handle connection creation and management safely in a multi-threaded environment

Fix the broken/incomplete implementation in `src/main/java/edu/kau/fcit/cpit252/DatabaseConnection.java` and make all tests pass.
