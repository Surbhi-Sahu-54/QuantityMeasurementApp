package com.apps.quantitymeasurement;

public class Weight {

    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {

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

    public WeightUnit getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBase(value);
    }

    public Weight convertTo(WeightUnit targetUnit) {

        double base = unit.convertToBase(value);
        double result = targetUnit.convertFromBase(base);

        return new Weight(result, targetUnit);
    }

    public Weight add(Weight other) {
        return add(other, this.unit);
    }

    public Weight add(Weight other, WeightUnit targetUnit) {

        double sumBase =
                this.unit.convertToBase(this.value)
                        + other.unit.convertToBase(other.value);

        double result = targetUnit.convertFromBase(sumBase);

        return new Weight(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Weight))
            return false;

        Weight other = (Weight) obj;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }
}