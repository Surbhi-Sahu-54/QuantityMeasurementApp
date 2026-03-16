package com.apps.quantitymeasurement;
import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
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

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.toBase(value);
    }

    private void validateSameCategory(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        if (!this.unit.getMeasurementType().equals(other.unit.getMeasurementType())) {
            throw new IllegalArgumentException("Cross-category operation is not allowed");
        }
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!this.unit.getMeasurementType().equals(targetUnit.getMeasurementType())) {
            throw new IllegalArgumentException("Target unit must belong to same measurement type");
        }
        double converted = targetUnit.fromBase(this.toBase());
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        validateSameCategory(other);
        if (!unit.supportsAddition() || !other.unit.supportsAddition()) {
            throw new UnsupportedOperationException("Addition not supported for this measurement type");
        }
        double resultBase = this.toBase() + other.toBase();
        return new Quantity<>(this.unit.fromBase(resultBase), this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!unit.supportsAddition() || !other.unit.supportsAddition()) {
            throw new UnsupportedOperationException("Addition not supported for this measurement type");
        }
        double resultBase = this.toBase() + other.toBase();
        return new Quantity<>(targetUnit.fromBase(resultBase), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        validateSameCategory(other);
        if (!unit.supportsSubtraction() || !other.unit.supportsSubtraction()) {
            throw new UnsupportedOperationException("Subtraction not supported for this measurement type");
        }
        double resultBase = this.toBase() - other.toBase();
        return new Quantity<>(this.unit.fromBase(resultBase), this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateSameCategory(other);
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        if (!unit.supportsSubtraction() || !other.unit.supportsSubtraction()) {
            throw new UnsupportedOperationException("Subtraction not supported for this measurement type");
        }
        double resultBase = this.toBase() - other.toBase();
        return new Quantity<>(targetUnit.fromBase(resultBase), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateSameCategory(other);
        if (!unit.supportsDivision() || !other.unit.supportsDivision()) {
            throw new UnsupportedOperationException("Division not supported for this measurement type");
        }

        double divisor = other.toBase();
        if (Math.abs(divisor) < EPSILON) {
            throw new ArithmeticException("Division by zero is not allowed");
        }

        return this.toBase() / divisor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getMeasurementType().equals(other.unit.getMeasurementType())) {
            return false;
        }

        return Math.abs(this.toBase() - other.getUnit().toBase(other.getValue())) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getMeasurementType(), Math.round(toBase() / EPSILON));
    }

    @Override
    public String toString() {
        return String.format("%.4f %s", value, unit.getUnitName());
    }
}