package com.apps.quantitymeasurement;

public class Length {

    private static final double EPSILON = 1e-5;

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    public boolean compare(Length that) {
        if (that == null) {
            return false;
        }
        return Double.compare(this.toBase(), that.toBase()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length that = (Length) o;
        return Math.abs(this.toBase() - that.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = this.toBase();
        double convertedValue = targetUnit.fromBase(baseValue);
        return new Length(convertedValue, targetUnit);
    }

    public Length add(Length other) {
        if (other == null) {
            throw new IllegalArgumentException("Other length cannot be null");
        }
        return add(other, this.unit);
    }

    public Length add(Length other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Operands and target unit cannot be null");
        }

        double sumBase = this.toBase() + other.toBase();
        double resultValue = targetUnit.fromBase(sumBase);
        return new Length(resultValue, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}