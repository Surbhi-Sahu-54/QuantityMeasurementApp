package com.apps.quantitymeasurement;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

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

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        double base = unit.convertToBase(value);
        double result = targetUnit.convertFromBase(base);

        return new QuantityWeight(result, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        double sumBase =
                this.unit.convertToBase(this.value)
                        + other.unit.convertToBase(other.value);

        double result = targetUnit.convertFromBase(sumBase);

        return new QuantityWeight(result, targetUnit);
    }
}