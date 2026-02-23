package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 0.0001;

    public Length(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (value < 0)
            throw new IllegalArgumentException("Value cannot be negative");

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
        return unit.convertToBase(value);
    }

    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double base = unit.convertToBase(value);
        double result = targetUnit.convertFromBase(base);

        return new Length(result, targetUnit);
    }

    public Length add(Length other) {
        return add(other, this.unit);
    }

    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        double sumBase =
                this.unit.convertToBase(this.value)
                        + other.unit.convertToBase(other.value);

        double result = targetUnit.convertFromBase(sumBase);

        return new Length(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Length))
            return false;

        Length other = (Length) obj;

        return Math.abs(this.toBase() - other.toBase()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }
}