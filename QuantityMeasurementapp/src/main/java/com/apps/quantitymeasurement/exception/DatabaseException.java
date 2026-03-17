package com.apps.quantitymeasurement.exception;

public class DatabaseException extends QuantityMeasurementException {

    /**
     * Creates database exception with message.
     *
     * @param message database-related error message
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Creates database exception with message and cause.
     *
     * @param message database-related error message
     * @param cause original exception
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Factory method for connection failure.
     *
     * @param details connection failure details
     * @param cause root cause
     * @return DatabaseException instance
     */
    public static DatabaseException connectionFailed(String details, Throwable cause) {
        return new DatabaseException("Database connection failed: " + details, cause);
    }

    /**
     * Factory method for query failure.
     *
     * @param query failed SQL or query description
     * @param cause root cause
     * @return DatabaseException instance
     */
    public static DatabaseException queryFailed(String query, Throwable cause) {
        return new DatabaseException("Query execution failed: " + query, cause);
    }

    /**
     * Factory method for transaction failure.
     *
     * @param operation failed operation
     * @param cause root cause
     * @return DatabaseException instance
     */
    public static DatabaseException transactionFailed(String operation, Throwable cause) {
        return new DatabaseException("Transaction failed during: " + operation, cause);
    }
}