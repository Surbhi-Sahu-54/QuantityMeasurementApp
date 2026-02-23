package com.apps.quantitymeasurement;
import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;
    private static final double EPSILON = 0.02;

    // UC13: Step 1 - Define Operations
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b), DIVIDE((a, b) -> a / b);
        private final DoubleBinaryOperator op;
        ArithmeticOperation(DoubleBinaryOperator op) { this.op = op; }
        public double compute(double v1, double v2) { return op.applyAsDouble(v1, v2); }
    }

    public Quantity(double value, U unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null nahi ho sakta");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }

    // UC13 & UC14: Centralized Arithmetic Logic
    private double performArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        // UC14: Support check (Temperature ke liye exception fekega)
        this.unit.validateOperationSupport(operation.name());

        if (other == null || !this.unit.getCategory().equals(other.unit.getCategory()))
            throw new IllegalArgumentException("Category mismatch");

        double base1 = this.unit.convertToBase(this.value);
        double base2 = other.unit.convertToBase(other.value);
        
        if (operation == ArithmeticOperation.DIVIDE && base2 == 0)
            throw new ArithmeticException("Zero division");

        return operation.compute(base1, base2);
    }

    // --- Public Methods (Satisfying UC1-UC14) ---

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit); 
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double res = performArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(targetUnit.convertFromBase(res)), targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other) {
        double res = performArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(this.unit.convertFromBase(res)), this.unit);
    }

    public double divide(Quantity<U> other) {
        return performArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    public Quantity<U> convertTo(U targetUnit) {
        double base = this.unit.convertToBase(this.value);
        return new Quantity<>(round(targetUnit.convertFromBase(base)), targetUnit);
    }

    private double round(double v) { return Math.round(v * 100.0) / 100.0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity<?> that)) return false;
        if (!this.unit.getCategory().equals(that.unit.getCategory())) return false;
        return Math.abs(this.unit.convertToBase(this.value) - that.unit.convertToBase(that.value)) < EPSILON;
    }
}