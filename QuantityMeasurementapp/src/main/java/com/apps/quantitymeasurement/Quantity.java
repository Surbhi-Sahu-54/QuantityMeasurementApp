package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

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

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBase(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBase();
        double result = targetUnit.convertFromBase(baseValue);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        double sumBase =
                this.toBase() +
                other.toBase();

        double result =
                targetUnit.convertFromBase(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getCategory().equals(other.unit.getCategory()))
            return false;

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}