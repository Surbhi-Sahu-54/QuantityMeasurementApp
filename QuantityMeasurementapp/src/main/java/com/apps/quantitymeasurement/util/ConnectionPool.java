package com.apps.quantitymeasurement.util;
import com.apps.quantitymeasurement.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * ConnectionPool
 *
 * Simple JDBC connection pool for managing reusable database connections.
 *
 * Responsibilities:
 * - Initialize a fixed number of database connections
 * - Provide connections on demand
 * - Reuse released connections
 * - Keep connection management centralized
 *
 * Important Note:
 * This is a lightweight educational pool implementation suitable for UC16.
 * In production systems, HikariCP or another mature pooling library is preferred.
 */
public class ConnectionPool {

    private static final Logger logger = Logger.getLogger(ConnectionPool.class.getName());
    private static ConnectionPool instance;

    private final List<Connection> availableConnections = new LinkedList<>();
    private final List<Connection> usedConnections = new LinkedList<>();

    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;
    private final String dbDriver;
    private final int poolSize;

    private ConnectionPool() {
        ApplicationConfig config = ApplicationConfig.getInstance();

        this.dbUrl = config.getProperty("db.url");
        this.dbUsername = config.getProperty("db.username");
        this.dbPassword = config.getProperty("db.password");
        this.dbDriver = config.getProperty("db.driver");
        this.poolSize = config.getIntProperty("db.pool.size", 3);

        initializePool();
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Initializes the pool with pre-created JDBC connections.
     */
    private void initializePool() {
        try {
            Class.forName(dbDriver);

            for (int index = 0; index < poolSize; index++) {
                availableConnections.add(createNewConnection());
            }

            logger.info("ConnectionPool initialized with " + poolSize + " connections.");
        } catch (ClassNotFoundException exception) {
            throw DatabaseException.connectionFailed("JDBC driver class not found", exception);
        } catch (SQLException exception) {
            throw DatabaseException.connectionFailed("Unable to initialize connection pool", exception);
        }
    }

    /**
     * Creates new JDBC connection.
     *
     * @return newly created connection
     * @throws SQLException if connection creation fails
     */
    private Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    /**
     * Borrows a connection from the pool.
     *
     * @return active JDBC connection
     */
    public synchronized Connection getConnection() {
        try {
            if (availableConnections.isEmpty()) {
                throw new DatabaseException("No available database connections in pool.");
            }

            Connection connection = availableConnections.remove(0);

            if (!isValidConnection(connection)) {
                logger.warning("Invalid connection found. Recreating connection.");
                connection = createNewConnection();
            }

            usedConnections.add(connection);
            return connection;
        } catch (SQLException exception) {
            throw DatabaseException.connectionFailed("Failed to acquire connection from pool", exception);
        }
    }

    /**
     * Returns connection back to the pool.
     *
     * @param connection used connection
     */
    public synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
        }
    }

    /**
     * Validates whether a connection is still usable.
     *
     * @param connection connection to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidConnection(Connection connection) {
        try {
            return connection != null && !connection.isClosed() && connection.isValid(2);
        } catch (SQLException exception) {
            return false;
        }
    }

    public synchronized int getAvailableConnectionCount() {
        return availableConnections.size();
    }

    public synchronized int getUsedConnectionCount() {
        return usedConnections.size();
    }

    /**
     * Closes all pooled connections.
     */
    public synchronized void shutdown() {
        try {
            for (Connection connection : availableConnections) {
                connection.close();
            }
            for (Connection connection : usedConnections) {
                connection.close();
            }
            availableConnections.clear();
            usedConnections.clear();
            logger.info("ConnectionPool shut down successfully.");
        } catch (SQLException exception) {
            throw DatabaseException.connectionFailed("Error shutting down connection pool", exception);
        }
    }
}