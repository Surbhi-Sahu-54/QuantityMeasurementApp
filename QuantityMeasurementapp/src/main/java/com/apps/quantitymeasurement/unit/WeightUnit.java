package com.apps.quantitymeasurement.unit;
public enum WeightUnit implements IMeasurable {

    GRAM(1.0),
    KILOGRAM(1000.0),
    POUND(453.59237),
    OUNCE(28.349523125);

    /**
     * Conversion factor from current unit to grams.
     */
    private final double conversionFactorToGrams;

    /**
     * Constructs WeightUnit with its conversion factor to grams.
     *
     * @param conversionFactorToGrams factor for conversion to base unit
     */
    WeightUnit(double conversionFactorToGrams) {
        this.conversionFactorToGrams = conversionFactorToGrams;
    }

    /**
     * Converts given value from current weight unit into grams.
     *
     * @param value value in current unit
     * @return equivalent value in grams
     */
    @Override
    public double toBaseUnit(double value) {
        return value * conversionFactorToGrams;
    }

    /**
     * Converts given base-unit value from grams into current weight unit.
     *
     * @param baseValue value in grams
     * @return equivalent value in current unit
     */
    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToGrams;
    }

    /**
     * Returns measurement category.
     *
     * @return WEIGHT
     */
    @Override
    public String getMeasurementType() {
        return "WEIGHT";
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
     * Resolves WeightUnit from unit name.
     *
     * @param unitName name of the unit
     * @return matching WeightUnit
     * @throws IllegalArgumentException if unit name is invalid
     */
    public static WeightUnit fromUnitName(String unitName) {
        for (WeightUnit unit : WeightUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid weight unit: " + unitName);
    }
}