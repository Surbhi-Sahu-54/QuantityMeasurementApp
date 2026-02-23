package com.apps.quantitymeasurement;
import java.util.Objects;


public class QuantityWeight {
	private final double value;
	private final WeightUnit unit;
	private static final double EPSILON = 1e-6;

	public QuantityWeight(double value, WeightUnit unit) {
		if (unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		if (!Double.isFinite(value))
			throw new IllegalArgumentException("Value must be finite");

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public WeightUnit getUnit() {
		return unit;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		QuantityWeight that = (QuantityWeight) obj;

		double thisBase = this.unit.convertToBase(this.value);
		double thatBase = that.unit.convertToBase(that.value);

		return Math.abs(thisBase - thatBase) < EPSILON;
	}

	@Override
	public int hashCode() {
		double baseValue = unit.convertToBase(value);
		return Objects.hash(Math.round(baseValue / EPSILON));
	}

	public QuantityWeight convertTo(WeightUnit targetUnit) {
		if (targetUnit == null)
			throw new IllegalArgumentException("Target unit cannot be null");

		double baseValue = unit.convertToBase(value);
		double convertedValue = targetUnit.convertFromBase(baseValue);

		return new QuantityWeight(convertedValue, targetUnit);
	}

	// Implicit target (first operand unit)
	public QuantityWeight add(QuantityWeight other) {
		return add(other, this.unit);
	}

	// Explicit target
	public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
		if (other == null || targetUnit == null)
			throw new IllegalArgumentException("Arguments cannot be null");

		double sumBase = this.unit.convertToBase(this.value) + other.unit.convertToBase(other.value);

		double finalValue = targetUnit.convertFromBase(sumBase);

		return new QuantityWeight(finalValue, targetUnit);
	}

	@Override
	public String toString() {
		return String.format("%.6f %s", value, unit);
	}
}

