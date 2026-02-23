package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    // Constructor
    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (value < 0)
            throw new IllegalArgumentException("Value cannot be negative");

        this.value = value;
        this.unit = unit;
    }

    // Getter
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert this quantity to base unit value
    private double toBase() {
        return unit.convertToBase(value);
    }

    // ================= CONVERSION =================

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getCategory().equals(targetUnit.getCategory()))
            throw new IllegalArgumentException("Different measurement category");

        double base = this.toBase();
        double result = targetUnit.convertFromBase(base);

        return new Quantity<>(result, targetUnit);
    }

    // ================= ADDITION =================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        if (!unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Different measurement category");

        double sumBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBase(sumBase);

        return new Quantity<>(result, targetUnit);
    }

    // ================= SUBTRACTION =================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        if (!unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Different measurement category");

        double diffBase = this.toBase() - other.toBase();

        if (diffBase < 0)
            throw new IllegalArgumentException("Result cannot be negative");

        double result = targetUnit.convertFromBase(diffBase);

        return new Quantity<>(result, targetUnit);
    }

    // ================= DIVISION =================

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other cannot be null");

        if (!unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Different measurement category");

        double base1 = this.toBase();
        double base2 = other.toBase();

        if (base2 == 0)
            throw new ArithmeticException("Cannot divide by zero");

        return base1 / base2;
    }

    // ================= EQUALS =================

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

    // ================= HASHCODE =================

    @Override
    public int hashCode() {
        return Double.hashCode(toBase());
    }

    // ================= TOSTRING =================

    @Override
    public String toString() {
        return value + " " + unit;
    }
}