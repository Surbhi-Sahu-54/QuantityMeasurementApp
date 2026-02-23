package com.apps.quantitymeasurement;

public class Quantity<U extends Enum<U>> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value))
            throw new IllegalArgumentException("Value cannot be NaN");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double getConversionFactor(U unit) {

        if (unit instanceof LengthUnit)
            return ((LengthUnit) unit).getConversionFactor();

        if (unit instanceof WeightUnit)
            return ((WeightUnit) unit).getConversionFactor();

        throw new IllegalArgumentException("Unsupported unit type");
    }

    private double toBase() {
        return value * getConversionFactor(unit);
    }

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = toBase();

        double result = baseValue / getConversionFactor(targetUnit);

        return new Quantity<>(result, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Different unit categories");

        double sumBase = this.toBase() + other.toBase();

        double result = sumBase / getConversionFactor(targetUnit);

        return new Quantity<>(result, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        return Double.compare(this.toBase(),
                ((Quantity<?>) other).toBase()) == 0;
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