package com.apps.quantitymeasurement;
import java.util.Objects;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (value < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
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

    // Convert to base unit (Kilogram)
    private double toBase() {
        return unit.convertToBase(value);
    }
    public Weight convertTo(WeightUnit targetUnit) {
        double baseValue = this.unit.convertToBase(this.value);
        double convertedValue = targetUnit.convertFromBase(baseValue);
        return new Weight(convertedValue, targetUnit);
    }
    public Weight add(Weight other, WeightUnit targetUnit) {
        double sumBase = this.toBase() + other.toBase();
        double resultValue = targetUnit.convertFromBase(sumBase);
        return new Weight(resultValue, targetUnit);
    }
    public Weight add(Weight other) {
        double sumBase = this.toBase() + other.toBase();
        double resultValue = unit.convertFromBase(sumBase);
        return new Weight(resultValue, unit);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Weight other = (Weight) obj;

        return Math.abs(this.toBase() - other.toBase()) < 1e-3;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBase());
    }
}