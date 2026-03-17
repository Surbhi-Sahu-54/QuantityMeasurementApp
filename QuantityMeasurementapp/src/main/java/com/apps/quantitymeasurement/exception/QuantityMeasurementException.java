package com.apps.quantitymeasurement.exception;

public class QuantityMeasurementException extends RuntimeException {

    /**
     * Constructs exception with message.
     *
     * @param message error message
     */
    public QuantityMeasurementException(String message) {
        super(message);
    }

    /**
     * Constructs exception with message and cause.
     *
     * @param message error message
     * @param cause root cause
     */
    public QuantityMeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}