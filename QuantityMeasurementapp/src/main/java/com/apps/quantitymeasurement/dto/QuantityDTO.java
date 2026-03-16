package com.apps.quantitymeasurement.dto;

import java.util.Objects;

public class QuantityDTO {

    private final double value;
    private final String unit;
    private final String measurementType;

    public QuantityDTO(double value, String unit, String measurementType) {
        this.value = value;
        this.unit = unit;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {
        return value + " " + unit + " (" + measurementType + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityDTO)) return false;

        QuantityDTO other = (QuantityDTO) obj;
        return Double.compare(value, other.value) == 0
                && Objects.equals(unit, other.unit)
                && Objects.equals(measurementType, other.measurementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit, measurementType);
    }
}