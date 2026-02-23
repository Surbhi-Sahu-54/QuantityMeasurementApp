package com.apps.quantitymeasurement;

public enum LengthUnit {

	  // Base unit = FEET
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double conversionFactor;

    /**
     * conversionFactor represents:
     * How much of FEET equals 1 unit of this type.
     */
    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Responsibility 1:
     * Convert value in this unit to the base unit (FEET).
     */
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    /**
     * Responsibility 2:
     * Convert base unit value (FEET) back to this unit.
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    /**
     * Responsibility 3:
     * Direct conversion from this unit to target unit.
     */
    public double convert(double value, LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        // Step 1: Convert to base unit (FEET)
        double baseValue = convertToBaseUnit(value);

        // Step 2: Convert base unit to target unit
        return targetUnit.convertFromBaseUnit(baseValue);
    }
}