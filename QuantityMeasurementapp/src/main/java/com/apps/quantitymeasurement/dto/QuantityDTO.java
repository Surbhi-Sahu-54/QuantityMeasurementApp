package com.apps.quantitymeasurement.dto;

import java.util.Objects;

/**
 * QuantityDTO
 *
 * Data Transfer Object representing a quantity value with unit and measurement type.
 */
public class QuantityDTO {

    private double value;
    private String unitName;
    private String measurementType;

    public QuantityDTO(double value, String unitName, String measurementType) {
        this.value = value;
        this.unitName = unitName;
        this.measurementType = measurementType;
    }

    public double getValue() {
        return value;
    }

    public String getUnitName() {
        return unitName;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    @Override
    public String toString() {
        return "QuantityDTO{" +
                "value=" + value +
                ", unitName='" + unitName + '\'' +
                ", measurementType='" + measurementType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof QuantityDTO other)) {
            return false;
        }
        return Double.compare(other.value, value) == 0
                && Objects.equals(unitName, other.unitName)
                && Objects.equals(measurementType, other.measurementType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unitName, measurementType);
    }
}