package edu.kau.fcit.cpit252;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private DatabaseConnection instance;
    private Connection connection;
    private static final String URL = "jdbc:sqlite:database.db";

    // TODO: Implement constructor and initialize database connection
    public DatabaseConnection() {
    }

    public DatabaseConnection getInstance() {
        // TODO: Implement getInstance method
        return new DatabaseConnection();
    }

    private void initializeConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL);
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(
                        "CREATE TABLE IF NOT EXISTS students (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL)"
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Error initializing database connection", e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                initializeConnection();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get database connection", e);
        }
        return connection;
    }

    public boolean executeTestQuery() {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute("SELECT 1");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        // TODO: Implement connection closing logic
    }
}