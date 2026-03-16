package com.apps.quantitymeasurement;

public enum WeightUnit implements IMeasurable {
    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.59237),
    OUNCE(28.349523125);

    private final double conversionFactorToGrams;

    WeightUnit(double conversionFactorToGrams) {
        this.conversionFactorToGrams = conversionFactorToGrams;
    }

    @Override
    public double toBase(double value) {
        return value * conversionFactorToGrams;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / conversionFactorToGrams;
    }

    @Override
    public String getMeasurementType() {
        return "WEIGHT";
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid weight unit: " + unitName);
    }
}