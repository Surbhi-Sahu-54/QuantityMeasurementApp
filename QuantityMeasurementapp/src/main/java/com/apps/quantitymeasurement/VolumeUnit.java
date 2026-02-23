package com.apps.quantitymeasurement;

public enum VolumeUnit implements IMeasurable {

    LITRE(1000.0, "VOLUME"),
    MILLILITRE(1.0, "VOLUME"),
    GALLON(3785.41, "VOLUME");

    private final double conversionFactor;
    private final String category;

    VolumeUnit(double conversionFactor, String category) {
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