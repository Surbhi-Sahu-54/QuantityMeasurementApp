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

    public QuantityWeight convertTo(WeightUnit targetUnit) {

        double base =
                unit.convertToBaseUnit(value);

        double result =
                targetUnit.convertFromBaseUnit(base);

        return new QuantityWeight(result, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {

        double sumBase =
                this.unit.convertToBaseUnit(this.value)
                        + other.unit.convertToBaseUnit(other.value);

        double result =
                targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityWeight(result, targetUnit);
    }
}