package com.apps.quantitymeasurement.quantity;
import com.apps.quantitymeasurement.unit.IMeasurable;

/**
 * Quantity
 *
 * Core domain object representing a numeric quantity with a measurable unit.
 *
 * Responsibilities:
 * - Store value + unit
 * - Support equality through base-unit normalization
 * - Support category-safe comparison and conversion
 *
 * This is the central value object of the quantity-measurement domain.
 */
public class Quantity {

    private final double value;
    private final IMeasurable unit;

    public Quantity(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }

    /**
     * Checks if two quantities are equal by converting both to base unit.
     *
     * @param other another quantity
     * @return true if equal, otherwise false
     */
    public boolean equivalentTo(Quantity other) {
        if (other == null) {
            return false;
        }

        if (!unit.getMeasurementType().equals(other.unit.getMeasurementType())) {
            return false;
        }

        double thisBase = unit.toBaseUnit(value);
        double thatBase = other.unit.toBaseUnit(other.value);

        return Math.abs(thisBase - thatBase) < 0.0001;
    }

    /**
     * Converts this quantity to a target unit.
     *
     * @param targetUnit target measurable unit
     * @return converted quantity
     */
    public Quantity convertTo(IMeasurable targetUnit) {
        if (!unit.getMeasurementType().equals(targetUnit.getMeasurementType())) {
            throw new IllegalArgumentException("Cannot convert between different measurement types.");
        }

        double baseValue = unit.toBaseUnit(value);
        double converted = targetUnit.fromBaseUnit(baseValue);
        return new Quantity(converted, targetUnit);
    }
}