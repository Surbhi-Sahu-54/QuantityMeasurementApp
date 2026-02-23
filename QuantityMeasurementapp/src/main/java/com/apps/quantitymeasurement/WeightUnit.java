package com.apps.quantitymeasurement;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1000.0, "WEIGHT"),
    GRAM(1.0, "WEIGHT");

    private final double conversionFactor;
    private final String category;

    WeightUnit(double conversionFactor, String category) {
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