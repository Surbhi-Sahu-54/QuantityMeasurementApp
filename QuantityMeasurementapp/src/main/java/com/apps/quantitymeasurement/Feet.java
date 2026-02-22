package com.apps.quantitymeasurement;

public class Feet {
	private final double value;   // immutable

    public Feet(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {

        // Same reference check
        if (this == obj) {
            return true;
        }

        // Null + type check
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Safe cast
        Feet other = (Feet) obj;

        // Floating comparison
        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
