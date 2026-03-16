package com.apps.quantitymeasurement;

public enum VolumeUnit implements IMeasurable {
    MILLILITRE(1.0),
    LITRE(1000.0),
    GALLON(3785.41);

    private final double conversionFactorToMillilitre;

    VolumeUnit(double conversionFactorToMillilitre) {
        this.conversionFactorToMillilitre = conversionFactorToMillilitre;
    }

    @Override
    public double toBase(double value) {
        return value * conversionFactorToMillilitre;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / conversionFactorToMillilitre;
    }

    @Override
    public String getMeasurementType() {
        return "VOLUME";
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public IMeasurable getUnitInstance(String unitName) {
        for (VolumeUnit unit : VolumeUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid volume unit: " + unitName);
    }
}