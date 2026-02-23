package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;

    // Constructor
    public Length(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    // Getter for value
    public double getValue() {
        return value;
    }

    // Getter for unit
    public LengthUnit getUnit() {
        return unit;
    }

    // Convert to inches (base unit)
    private double toInches() {
        return this.value * this.unit.getConversionFactor();
    }

    // Addition method
    public Length add(Length other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Other length or target unit cannot be null");
        }

        double thisInches = this.toInches();
        double otherInches = other.toInches();

        double sumInches = thisInches + otherInches;

        double resultValue = sumInches / targetUnit.getConversionFactor();

        return new Length(resultValue, targetUnit);
    }

    // Equals method
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Length other = (Length) obj;

        return Double.compare(this.toInches(), other.toInches()) == 0;
    }

    // HashCode
    @Override
    public int hashCode() {
        return Double.hashCode(toInches());
    }

    // toString
    @Override
    public String toString() {
        return value + " " + unit;
    }
}