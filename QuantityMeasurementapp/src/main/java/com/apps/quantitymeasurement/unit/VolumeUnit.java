package com.apps.quantitymeasurement.unit;
public enum VolumeUnit implements IMeasurable {

    MILLILITER(1.0),
    LITER(1000.0),
    GALLON(3785.41);

    /**
     * Conversion factor from current unit to milliliters.
     */
    private final double conversionFactorToMilliliters;

    /**
     * Constructs VolumeUnit with its conversion factor to milliliters.
     *
     * @param conversionFactorToMilliliters factor for conversion to base unit
     */
    VolumeUnit(double conversionFactorToMilliliters) {
        this.conversionFactorToMilliliters = conversionFactorToMilliliters;
    }

    /**
     * Converts given value from current volume unit into milliliters.
     *
     * @param value value in current unit
     * @return equivalent value in milliliters
     */
    @Override
    public double toBaseUnit(double value) {
        return value * conversionFactorToMilliliters;
    }

    /**
     * Converts given base-unit value from milliliters into current volume unit.
     *
     * @param baseValue value in milliliters
     * @return equivalent value in current unit
     */
    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToMilliliters;
    }

    /**
     * Returns measurement category.
     *
     * @return VOLUME
     */
    @Override
    public String getMeasurementType() {
        return "VOLUME";
    }

    /**
     * Returns unit name.
     *
     * @return enum constant name
     */
    @Override
    public String getUnitName() {
        return name();
    }

    /**
     * Resolves VolumeUnit from unit name.
     *
     * @param unitName name of the unit
     * @return matching VolumeUnit
     * @throws IllegalArgumentException if unit name is invalid
     */
    public static VolumeUnit fromUnitName(String unitName) {
        for (VolumeUnit unit : VolumeUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid volume unit: " + unitName);
    }
}