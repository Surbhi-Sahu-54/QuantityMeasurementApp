package com.apps.quantitymeasurement;

import java.util.Objects;

public class Weight {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        return new Weight(targetUnit.fromBase(toBase()), targetUnit);
    }

    public Weight add(Weight other) {
        if (other == null) {
            throw new IllegalArgumentException("Other weight cannot be null");
        }
        return add(other, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Other weight and target unit cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        return new Weight(targetUnit.fromBase(sumBase), targetUnit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Weight)) return false;

        Weight other = (Weight) obj;
        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBase() / EPSILON));
    }
}