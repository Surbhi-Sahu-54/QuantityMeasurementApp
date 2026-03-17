package com.apps.quantitymeasurement.unit;
public enum TemperatureUnit implements IMeasurable {

    CELSIUS,
    FAHRENHEIT,
    KELVIN;

    /**
     * Converts given temperature from current unit into Celsius.
     *
     * @param value value in current unit
     * @return equivalent value in Celsius
     */
    @Override
    public double toBaseUnit(double value) {
        switch (this) {
            case CELSIUS:
                return value;
            case FAHRENHEIT:
                return (value - 32) * 5 / 9;
            case KELVIN:
                return value - 273.15;
            default:
                throw new IllegalArgumentException("Unsupported temperature unit.");
        }
    }

    /**
     * Converts given base-unit value from Celsius into current temperature unit.
     *
     * @param baseValue value in Celsius
     * @return equivalent value in current unit
     */
    @Override
    public double fromBaseUnit(double baseValue) {
        switch (this) {
            case CELSIUS:
                return baseValue;
            case FAHRENHEIT:
                return (baseValue * 9 / 5) + 32;
            case KELVIN:
                return baseValue + 273.15;
            default:
                throw new IllegalArgumentException("Unsupported temperature unit.");
        }
    }

    /**
     * Returns measurement category.
     *
     * @return TEMPERATURE
     */
    @Override
    public String getMeasurementType() {
        return "TEMPERATURE";
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
     * Resolves TemperatureUnit from unit name.
     *
     * @param unitName name of the unit
     * @return matching TemperatureUnit
     * @throws IllegalArgumentException if unit name is invalid
     */
    public static TemperatureUnit fromUnitName(String unitName) {
        for (TemperatureUnit unit : TemperatureUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid temperature unit: " + unitName);
    }
}