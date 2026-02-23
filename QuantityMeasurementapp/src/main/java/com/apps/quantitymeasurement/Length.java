package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    // Constructor
    public Length(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // Convert this length to base unit (inches)
    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    // Convert to target unit
    public Length convertTo(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = this.toBase();

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new Length(convertedValue, targetUnit);
    }

    // Add using same unit as current object
    public Length add(Length other) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        return add(other, this.unit);
    }

    // Add with target unit
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        double sumBase =
                this.toBase() + other.toBase();

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
    }

    // Equals
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
        return Objects.hash(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }


    // MAIN TEST
    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Equal: " + l1.equals(l2));

        Length result = l1.add(l2, LengthUnit.YARDS);

        System.out.println("Result in yards: " + result);
    }

}