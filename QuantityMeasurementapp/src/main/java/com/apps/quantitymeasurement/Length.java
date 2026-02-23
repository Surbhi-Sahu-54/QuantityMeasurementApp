package com.apps.quantitymeasurement;

public class Length {

	  private final double value;
	    private final LengthUnit unit;

	    private static final double EPSILON = 0.00001;

	    public Length(double value, LengthUnit unit) {
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

	    public LengthUnit getUnit() {
	        return unit;
	    }

	    // ----------------------------
	    // Strict comparison
	    // ----------------------------
	    public boolean compare(Length that) {
	        if (that == null) {
	            return false;
	        }

	        double thisBase = this.unit.convertToBaseUnit(this.value);
	        double thatBase = that.unit.convertToBaseUnit(that.value);

	        return Double.compare(thisBase, thatBase) == 0;
	    }

	    // ----------------------------
	    // equals with tolerance
	    // ----------------------------
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof Length)) return false;

	        Length that = (Length) o;

	        double thisBase = this.unit.convertToBaseUnit(this.value);
	        double thatBase = that.unit.convertToBaseUnit(that.value);

	        return Math.abs(thisBase - thatBase) < EPSILON;
	    }

	    @Override
	    public int hashCode() {
	        double baseValue = unit.convertToBaseUnit(value);
	        return Double.hashCode(baseValue);
	    }

	    // ----------------------------
	    // UC5 – Conversion
	    // ----------------------------
	    public Length convertTo(LengthUnit targetUnit) {
	        if (targetUnit == null) {
	            throw new IllegalArgumentException("Target unit cannot be null");
	        }

	        double baseValue = this.unit.convertToBaseUnit(this.value);
	        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

	        return new Length(convertedValue, targetUnit);
	    }

	    // ----------------------------
	    // UC6 – Implicit Target
	    // ----------------------------
	    public Length add(Length that) {
	        if (that == null) {
	            throw new IllegalArgumentException("Operand cannot be null");
	        }
	        return add(that, this.unit);
	    }

	    // ----------------------------
	    // UC7 – Explicit Target
	    // ----------------------------
	    public Length add(Length that, LengthUnit targetUnit) {
	        if (that == null || targetUnit == null) {
	            throw new IllegalArgumentException("Operand and target unit cannot be null");
	        }

	        if (!Double.isFinite(that.value)) {
	            throw new IllegalArgumentException("Measurement values must be finite");
	        }

	        return addAndConvert(that, targetUnit);
	    }

	    /**
	     * Private utility method to centralize addition logic.
	     */
	    private Length addAndConvert(Length that, LengthUnit targetUnit) {

	        double sumInBase =
	                this.unit.convertToBaseUnit(this.value) +
	                that.unit.convertToBaseUnit(that.value);

	        double finalValue = targetUnit.convertFromBaseUnit(sumInBase);

	        return new Length(finalValue, targetUnit);
	    }

	    @Override
	    public String toString() {
	        return String.format("%.2f %s", value, unit);
	    }

	    // Standalone test
	    public static void main(String[] args) {

	        Length l1 = new Length(1.0, LengthUnit.FEET);
	        Length l2 = new Length(12.0, LengthUnit.INCHES);

	        System.out.println("Are lengths equal? " + l1.equals(l2));

	        Length l3 = new Length(1, LengthUnit.YARDS);
	        Length l4 = new Length(36, LengthUnit.INCHES);
	        System.out.println("Are lengths equal? " + l3.equals(l4));

	        Length l5 = new Length(100, LengthUnit.CENTIMETERS);
	        Length l6 = new Length(39.3701, LengthUnit.INCHES);
	        System.out.println("Are lengths equal? " + l5.equals(l6));

	        System.out.println("Result in YARDS: " + l1.add(l2, LengthUnit.YARDS));
	        System.out.println("Result in INCHES: " + l1.add(l2, LengthUnit.INCHES));
	    }
	}