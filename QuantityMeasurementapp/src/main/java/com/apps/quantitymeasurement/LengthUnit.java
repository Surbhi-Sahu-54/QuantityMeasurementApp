package com.apps.quantitymeasurement;

public enum LengthUnit implements IMeasurable {

    FEET(12.0, "LENGTH"),
    INCHES(1.0, "LENGTH"),
    YARDS(36.0, "LENGTH"),
    CENTIMETERS(0.393701, "LENGTH");

    private final double conversionFactor;
    private final String category;

    LengthUnit(double conversionFactor, String category) {
        this.conversionFactor = conversionFactor;
        this.category = category;
    }

    @Override
    public double convertToBase(double value) {
        return value * conversionFactor;
    }

    @Override
    public double convertFromBase(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getCategory() {
        return category;
    }
}