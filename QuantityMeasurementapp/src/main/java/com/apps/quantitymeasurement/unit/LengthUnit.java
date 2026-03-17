package com.apps.quantitymeasurement.unit;
public enum LengthUnit implements IMeasurable {

    INCH(1.0),
    FEET(12.0),
    YARD(36.0),
    CENTIMETER(0.393700787),
    METER(39.3700787);

    /**
     * Conversion factor from current unit to inches.
     */
    private final double conversionFactorToInches;

    /**
     * Constructs LengthUnit with its conversion factor to inches.
     *
     * @param conversionFactorToInches factor for conversion to base unit
     */
    LengthUnit(double conversionFactorToInches) {
        this.conversionFactorToInches = conversionFactorToInches;
    }

    /**
     * Converts given value from current length unit into inches.
     *
     * @param value value in current unit
     * @return equivalent value in inches
     */
    @Override
    public double toBaseUnit(double value) {
        return value * conversionFactorToInches;
    }

    /**
     * Converts given base-unit value from inches into current length unit.
     *
     * @param baseValue value in inches
     * @return equivalent value in current unit
     */
    @Override
    public double fromBaseUnit(double baseValue) {
        return baseValue / conversionFactorToInches;
    }

    /**
     * Returns measurement category.
     *
     * @return LENGTH
     */
    @Override
    public String getMeasurementType() {
        return "LENGTH";
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
     * Resolves LengthUnit from unit name.
     *
     * @param unitName name of the unit
     * @return matching LengthUnit
     * @throws IllegalArgumentException if unit name is invalid
     */
    public static LengthUnit fromUnitName(String unitName) {
        for (LengthUnit unit : LengthUnit.values()) {
            if (unit.getUnitName().equalsIgnoreCase(unitName)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Invalid length unit: " + unitName);
    }
}