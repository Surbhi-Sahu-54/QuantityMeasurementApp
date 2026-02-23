package com.apps.quantitymeasurement;
public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    // Base unit is Kilogram
    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    public double convertToBase(double value) {
        return value * conversionFactor;
    }

    public double convertFromBase(double baseValue) {
        return baseValue / conversionFactor;
    }

    public double convert(double value, WeightUnit targetUnit) {
        double baseValue = convertToBase(value);
        return targetUnit.convertFromBase(baseValue);
    }
}