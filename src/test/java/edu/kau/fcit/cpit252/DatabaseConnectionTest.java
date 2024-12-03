package edu.kau.fcit.cpit252;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @Test
    public void testSingletonInstance() {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        assertEquals(instance1, instance2, "Multiple instances were created");
    }

    @Test
    public void testDatabaseConnection() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        assertNotNull(instance.getConnection(), "Connection should not be null");
    }

    @Test
    public void testTestQuery() {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        assertTrue(instance.executeTestQuery(), "Test query should execute successfully");
    }

    @Test
    public void testMultiThreadAccess() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            assertNotNull(instance);
        });

        Thread t2 = new Thread(() -> {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            assertNotNull(instance);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

