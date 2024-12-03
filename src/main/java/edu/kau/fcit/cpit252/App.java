package edu.kau.fcit.cpit252;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        try {
            // Get database connection instance
            DatabaseConnection dbConnection = DatabaseConnection.getInstance();

            // Test the connection
            System.out.println("Testing database connection...");
            if (dbConnection.executeTestQuery()) {
                System.out.println("Database connection successful!");
            }

            // Insert some data
            Connection conn = dbConnection.getConnection();
            String insertSQL = "INSERT INTO students (name) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                pstmt.setString(1, "Khalid");
                pstmt.executeUpdate();
                System.out.println("Inserted test data successfully!");
            }

            // Get the inserted data
            String selectSQL = "SELECT * FROM students";
            try (PreparedStatement pstmt = conn.prepareStatement(selectSQL);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println("\nRetrieved data from database:");
                while (rs.next()) {
                    System.out.printf("ID: %d, Name: %s%n",
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }

            // Validate singleton behavior
            DatabaseConnection anotherInstance = DatabaseConnection.getInstance();
            System.out.println("\nChecking Singleton behavior:");
            System.out.println("Are instances same? " + (dbConnection == anotherInstance));

            // Test multi-threading
            System.out.println("\nTesting multi-threaded access:");
            Runnable dbTask = () -> {
                DatabaseConnection threadInstance = DatabaseConnection.getInstance();
                System.out.println("Thread " + Thread.currentThread().getName() +
                        " got connection instance: " + threadInstance.hashCode());
            };

            Thread t1 = new Thread(dbTask, "Thread-1");
            Thread t2 = new Thread(dbTask, "Thread-2");
            t1.start();
            t2.start();
            t1.join();
            t2.join();

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Clean up resources
            DatabaseConnection.getInstance().closeConnection();
        }
    }
}
