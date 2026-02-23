package com.apps.quantitymeasurement;

public interface IMeasurable {
    double convertToBase(double value);
    double convertFromBase(double baseValue);
    String getCategory();

    // UC14: Default true - Sab units (Length, Weight, Volume) arithmetic support karengi
    default boolean supportsArithmetic() {
        return true; 
    }

    // UC14: Helper to throw exception if not supported
    default void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(getCategory() + " does not support " + operation);
        }
    }
}